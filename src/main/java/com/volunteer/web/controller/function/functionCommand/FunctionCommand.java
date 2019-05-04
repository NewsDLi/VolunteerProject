package com.volunteer.web.controller.function.functionCommand;

import com.volunteer.model.UserInfo;
import com.volunteer.model.UserPower;

import java.util.List;

public class FunctionCommand {
    private List<UserPower> userPowers;
    private UserInfo userInfo;

    public List<UserPower> getUserPowers() {
        return userPowers;
    }

    public void setUserPowers(List<UserPower> userPowers) {
        this.userPowers = userPowers;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}