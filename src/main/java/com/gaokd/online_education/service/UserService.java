package com.gaokd.online_education.service;

import com.gaokd.online_education.model.entity.User;

import java.util.Map;

public interface UserService {

    int save(Map<String,String> userInfo);
    User findByName(String name);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer userId);
}
