package com.volunteer.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.volunteer.constant.CommonConstant;
import com.volunteer.constant.CommonEnum;
import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoTag;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;
import com.volunteer.web.manager.UserInfoManager;
import com.volunteer.web.manager.UserInfoTagManager;

/**
 * @author NewsDLee
 * @date 2019年4月22日22:12:20
 * @desc 用户信息的导入和导出
 */
@Controller
public class UserInfoImport {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserInfoManager userInfoManager;
	
	@Autowired
	private UserInfoTagManager userInfoTagManager;

	private static final Integer XLS_SHEET_1 = 0;
	
	
	/**
     * 下载模板
     */
    @RequestMapping(value = "/export.xlsx", method = RequestMethod.GET)
    public void downloadTemplate(HttpServletResponse response){
        String path = "excel/volunteer-export-import.xlsx";
        OutputStream outputStream = null;
        // HttpServletResponse Header设置
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        File file = new File(Thread.currentThread().getContextClassLoader().getResource(path).getPath());
        String fileName = file.getName();
        try{
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            outputStream = response.getOutputStream();
            InputStream inputStream = null;
            inputStream = new FileInputStream(file);
            XSSFWorkbook xlsx = new XSSFWorkbook(inputStream);
            if(xlsx != null){
                LOGGER.info("下载自定义订单状态模板");
                xlsx.write(outputStream);
            }
        }catch (Exception e){
            LOGGER.error("the /export.xlsx exception of :{}", e);
        }finally{
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOGGER.error("finally代码块中outputStream.close()报错！错误信息：{}", e);
                }
            }
        }
    }
    
    /**
     * 导入订单自定义订单状态
     * @param request
     * @return
     */
    @RequestMapping(value = "/import.json",method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Object> customStatusImport(@RequestParam(value = "excelFile") MultipartFile file, HttpServletRequest request){
    	String fileName = file.getOriginalFilename();
    	if (StringUtils.isBlank(fileName)){
    		return ApiResponse.build(ResponseStatus.FAIL, "");
    	}
        InputStream cacher = null;
        try {
            cacher = file.getInputStream();
            
            // 获取解析excel信息集合
            List<Object> userInfo = getUserInfo(cacher);
            boolean isSuccess = doHandleUserInfo(userInfo);
        } catch (IOException e) {
        	
        }
        return ApiResponse.build(ResponseStatus.SUCCESS, "");
    }
    
    // 处理excel解析完成的数据
    private boolean doHandleUserInfo(List<Object> userInfo) {
    	if (null == userInfo || userInfo.size() == 0){
    		return false;
    	}
    	for (Object object : userInfo) {
    		Map<String, Object> info = (Map<String, Object>) object;
    		// 用户信息
    		UserInfo user = (UserInfo) info.get("userInfo");
    		// 查询导入的用户是否已经存在
    		Integer checkUserIsExist = userInfoManager.checkUserIsExist(user.getIdCard());
    		if(null != checkUserIsExist){
    			continue;
    		}
    		
    		
    		Long userId = userInfoManager.insertUserInfo(user);
    		// 上过的课程
    		Map<String,Integer> havingClass = (Map<String, Integer>) info.get("havingClass");
    		UserInfoTag infoTag = null;
    		for(Map.Entry<String,Integer> entry : havingClass.entrySet()){
    			infoTag = new UserInfoTag();
    			infoTag.setTagName(CommonEnum.getValue(entry.getKey()));
    			infoTag.setTagCount(entry.getValue());
    			infoTag.setUserId(userId);
    			infoTag.setType(CommonConstant.TYPE_CLASS);
    			int insertUserInfoTag = userInfoTagManager.insertUserInfoTag(infoTag);
    		}
    		// 义工岗位
    		List<String> asList = (List<String>) info.get("post");
    		if(asList != null && asList.size()>0){
    			for (String string : asList) {
    				infoTag = new UserInfoTag();
    				infoTag.setUserId(userId);
    				infoTag.setTagName(string);
    				infoTag.setType(CommonConstant.TYPE_POST);
    				infoTag.setTagCount(1);
    				userInfoTagManager.insertUserInfoTag(infoTag);
				}
    		}
    		
		}
		return true;
	}

	private List<Object> getUserInfo(InputStream inputStream){
        // 获取HSSFSheet
    	XSSFSheet sheet = getHSSFSheet(inputStream);
        int lastRowNum = sheet.getLastRowNum();
        if(lastRowNum == 0){
        	return null;
        }
        List<Object> object = new ArrayList<>();
        Date date = new Date();
        for(int i=3; i<=lastRowNum; i++){
            Row row = sheet.getRow(i);
            if(null == row){
                LOGGER.info("excel中该行：{}没有数据！", i+1);
                continue;
            }
            // 校验订单号是否为空
            Cell cell = row.getCell(0);
            Cell cell1 = row.getCell(1);
            Cell cell2 = row.getCell(2);
            Cell cell3 = row.getCell(3);
            Cell cell4 = row.getCell(4);
            Cell cell5 = row.getCell(5);
            row.getCell(6).setCellType(CellType.STRING);
            Cell cell6 = row.getCell(6);
            row.getCell(7).setCellType(CellType.STRING);
            Cell cell7 = row.getCell(7);
            
            row.getCell(8).setCellType(CellType.STRING);
            Cell cell8 = row.getCell(8);
            row.getCell(9).setCellType(CellType.STRING);
            Cell cell9 = row.getCell(9);
            row.getCell(10).setCellType(CellType.STRING);
            Cell cell10 = row.getCell(10);
            row.getCell(11).setCellType(CellType.STRING);
            Cell cell11 = row.getCell(11);
            row.getCell(12).setCellType(CellType.STRING);
            Cell cell12 = row.getCell(12);
            row.getCell(13).setCellType(CellType.STRING);
            Cell cell13 = row.getCell(13);
            row.getCell(14).setCellType(CellType.STRING);
            Cell cell14 = row.getCell(14);
            row.getCell(15).setCellType(CellType.STRING);
            Cell cell15 = row.getCell(15);
            // 姓名
            String name = cell.getStringCellValue();
            // 性别
            String sex = cell1.getStringCellValue();
            // 身份证
            String idCard = cell2.getStringCellValue();
            // 工作单位
            String workUnit = cell3.getStringCellValue();
            // 手机号
            String phoneNumber = cell4.getStringCellValue();
            // 民族
            String nation = cell5.getStringCellValue();
            // 籍贯
            String birthplace = cell6.getStringCellValue();
            // 义工岗位
            String coursed = cell7.getStringCellValue();
            // 了凡厚道
            String lfhd = cell8.getStringCellValue();
            // 慈爱期数
            String caqs = cell9.getStringCellValue();
            // 孝道期数
            String xdqs = cell10.getStringCellValue();
            // 改过期数
            String ggqs = cell11.getStringCellValue();
            // 慈爱亲子
            String caqz = cell12.getStringCellValue();
            // 组别
            String group = cell13.getStringCellValue();
            // 角色
            String role = cell14.getStringCellValue();
            // 是否组长
            String groupLeader = cell15.getStringCellValue();
            
            // 存放解析一行的数据
            Map<String, Object> param = new HashMap<>();
            // 用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setName(name);
            userInfo.setSex(getSex(sex));
            userInfo.setIdCard(idCard);
            userInfo.setWorker(workUnit);
            userInfo.setLoginPhone(phoneNumber);
            userInfo.setGroupTeam(StringUtils.isBlank(group) ? null : Integer.valueOf(group));
            userInfo.setIsGroupLeader(StringUtils.isBlank(groupLeader) ? false : (groupLeader.equals("是") ? true: false));
            userInfo.setNation(nation);
            userInfo.setBirthplace(birthplace);
            userInfo.setLifecycle(1);
            userInfo.setVersion(date);
            userInfo.setRoleId(role.equals("教授")? 2L : 1L);
            param.put("userInfo", userInfo);
            
            // 上过的课程
            Map<String,Integer> map = new HashMap<>();
            map.put(CommonConstant.LFHD, StringUtils.isBlank(lfhd) ? 0 : Integer.valueOf(lfhd));
            map.put(CommonConstant.CAQS, StringUtils.isBlank(caqs) ? 0 : Integer.valueOf(caqs));
            map.put(CommonConstant.XDQS, StringUtils.isBlank(xdqs) ? 0 : Integer.valueOf(xdqs));
            map.put(CommonConstant.GGQS, StringUtils.isBlank(ggqs) ? 0 : Integer.valueOf(ggqs));
            map.put(CommonConstant.CAQZ, StringUtils.isBlank(caqz) ? 0 : Integer.valueOf(caqz));
            param.put("havingClass", map);
            
            if(StringUtils.isNotBlank(coursed)){
            	// 义工岗位
            	List<String> asList = Arrays.asList(coursed.split("\\|"));
            	param.put("post", asList);
            }
            object.add(param);
        }
        return object;
    }

    /**
     * 获取HSSFSheet
     * @param inputStream
     * @return
     */
    private XSSFSheet getHSSFSheet(InputStream inputStream){
        XSSFWorkbook importXls = null;
        try {
            importXls = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            LOGGER.error("创建HSSFWorkbook过程报错！：{}", e);
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return importXls.getSheetAt(XLS_SHEET_1);
    }

    // 男是0，女是1，其他为男
    private int getSex(String sex){
    	if("男".equals(sex)){
    		return 0;
    	}else if("女".equals(sex)){
    		return 1;
    	}
    	return 0;
    }
}