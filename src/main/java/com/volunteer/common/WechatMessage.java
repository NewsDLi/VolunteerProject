package com.volunteer.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 微信登录接口返回信息
 *
 * @author yuan
 *
 */
public class WechatMessage {

        /**
         * 0 – 成功
         * 2010 – 无效的code
         * 1001 – 系统错误
         */
        private Integer errcode;

        /**
         * 错误信息,当errCode非0时返回
         */
        private String errmsg;

        /**
         * 公众号的appid
         */
        @JSONField(name="AppId")
        private String appId;

        /**
         * 用户在当前公众号下的唯一标识
         */
        @JSONField(name="OpenId")
        private String openId;

        /**
         * 用户在多个公众号下的唯一标识
         */
        @JSONField(name="UnionId")
        private String unionId;

        /**
         * 0: 非微信会员
         * 1: 是微信会员
         */
        @JSONField(name="IsWechatMember")
        private Integer isWechatMember;

        @JSONField(name="Mobile")
        private String mobile;

        @JSONField(name="MemberId")
        private String memberId;


        public Integer getErrcode(){
            return errcode;
        }


        public void setErrcode(Integer errcode){
            this.errcode = errcode;
        }


        public String getErrmsg(){
            return errmsg;
        }


        public void setErrmsg(String errmsg){
            this.errmsg = errmsg;
        }


        public String getAppId(){
            return appId;
        }


        public void setAppId(String appId){
            this.appId = appId;
        }





        public String getOpenId(){
            return openId;
        }



        public void setOpenId(String openId){
            this.openId = openId;
        }


        public String getUnionId(){
            return unionId;
        }


        public void setUnionId(String unionId){
            this.unionId = unionId;
        }


        public Integer getIsWechatMember(){
            return isWechatMember;
        }


        public void setIsWechatMember(Integer isWechatMember){
            this.isWechatMember = isWechatMember;
        }


        public String getMobile(){
            return mobile;
        }


        public void setMobile(String mobile){
            this.mobile = mobile;
        }


        public String getMemberId(){
            return memberId;
        }


        public void setMemberId(String memberId){
            this.memberId = memberId;
        }

}
