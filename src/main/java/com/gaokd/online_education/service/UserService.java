package com.gaokd.online_education.service;

import com.gaokd.online_education.model.entity.User;

import java.util.Map;

public interface UserService {
    /**
     * 保存用户信息 （注册）
     * @param userInfo
     * @return
     */
    int save(Map<String,String> userInfo);

    /**
     * 根据姓名查找用户信息
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 根据电话号码和密码查找
     * @param phone
     * @param pwd
     * @return
     */
    String findByPhoneAndPwd(String phone, String pwd);

    /**
     *
     * @param userId
     * @return
     */
    User findByUserId(Integer userId);
}
