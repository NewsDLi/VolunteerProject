package com.volunteer.web.dao;

import com.volunteer.model.WechatInfo;
import com.volunteer.model.WechatInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WechatInfoMapper {
    int countByExample(WechatInfoExample example);

    int deleteByExample(WechatInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WechatInfo record);

    int insertSelective(WechatInfo record);

    List<WechatInfo> selectByExample(WechatInfoExample example);

    WechatInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WechatInfo record, @Param("example") WechatInfoExample example);

    int updateByExample(@Param("record") WechatInfo record, @Param("example") WechatInfoExample example);

    int updateByPrimaryKeySelective(WechatInfo record);

    int updateByPrimaryKey(WechatInfo record);

	WechatInfo getWechartInfoByUserId(Long id);
}