package com.volunteer.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.volunteer.model.UserInfo;
import com.volunteer.model.UserInfoTag;
import com.volunteer.response.ApiResponse;
import com.volunteer.response.ResponseStatus;

/**
 * @author NewsDLee
 * @date 2019年4月22日22:12:20
 * @desc 用户信息的导入和导出
 */
@Controller
public class UserInfoImport {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
            
            getUserInfo(cacher);
            // 更新商品索引信息
        } catch (IOException e) {
        }
        return ApiResponse.build(ResponseStatus.SUCCESS, "");
    }
    
    private void getUserInfo(InputStream inputStream){
        // 获取HSSFSheet
    	XSSFSheet sheet = getHSSFSheet(inputStream);
        int lastRowNum = sheet.getLastRowNum();
        if(lastRowNum == 0){
        	return;
//            return null;
        }
        for(int i=2; i<=lastRowNum; i++){
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
            // 上过的课程
            String coursed = cell5.getStringCellValue();
            // 了凡厚道
            String lfhd = cell6.getStringCellValue();
            // 慈爱期数
            String caqs = cell7.getStringCellValue();
            // 孝道期数
            String xdqs = cell8.getStringCellValue();
            // 改过期数
            String ggqs = cell9.getStringCellValue();
            // 慈爱亲子
            String caqz = cell10.getStringCellValue();
            // 组别
            String group = cell11.getStringCellValue();
            // 角色
            String role = cell12.getStringCellValue();
            // 是否组长
            String groupLeader = cell12.getStringCellValue();
            
            // 用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setName(name);
            userInfo.setSex(getSex(sex));
            userInfo.setIdCard(idCard);
            userInfo.setWorker(workUnit);
            userInfo.setLoginPhone(phoneNumber);
            userInfo.setGroup(StringUtils.isBlank(group) ? null : Integer.valueOf(group));
            userInfo.setIsGroupLeader(StringUtils.isBlank(group) ? false : (groupLeader.equals("是") ? true: false));
            
            
            Map<String,Integer> map = new HashMap<>();
            map.put("lfhd", StringUtils.isBlank(lfhd) ? 0 : Integer.valueOf(lfhd));
            map.put("caqs", StringUtils.isBlank(caqs) ? 0 : Integer.valueOf(caqs));
            map.put("xdqs", StringUtils.isBlank(xdqs) ? 0 : Integer.valueOf(xdqs));
            map.put("ggqs", StringUtils.isBlank(ggqs) ? 0 : Integer.valueOf(ggqs));
            map.put("caqz", StringUtils.isBlank(caqz) ? 0 : Integer.valueOf(caqz));
            List<UserInfoTag> listInfoTags = new ArrayList<UserInfoTag>();
            // 用户信息标签
            UserInfoTag infoTag = new UserInfoTag();
            // 次数
            infoTag.setTagName("了凡厚道");
            listInfoTags.add(infoTag);
        }
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
