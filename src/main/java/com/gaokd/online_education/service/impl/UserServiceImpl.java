package com.gaokd.online_education.service.impl;

import com.gaokd.online_education.model.entity.User;
import com.gaokd.online_education.mapper.UserMapper;
import com.gaokd.online_education.service.UserService;
import com.gaokd.online_education.utils.CommonUtils;
import com.gaokd.online_education.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @param phone
     * @param pwd
     * @return
     */
    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findByPhoneAndPwd(phone,CommonUtils.MD5(pwd));
        if (user==null){
            return null;
        }else {
            String token = JWTUtils.geneJsonWebToken(user);
            return token;
        }
    }

    @Override
    public User findByUserId(Integer userId) {
        User user = userMapper.findByUserId(userId);
        /**
         *密码一般是不能返回的,或者 加上@JsonIgnore
         */
        user.setPwd("");
        return user;
    }

    @Override
    public int save(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);
        if (user!=null){
            return userMapper.save(user);
        }else{
            return -1;
        }
    }

    /**
     * 解析user对象
     * @param userInfo
     * @return
     */
    private User parseToUser(Map<String, String> userInfo) {
        if (userInfo.containsKey("phone")&&userInfo.containsKey("pwd")&&userInfo.containsKey("name")){
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setPhone(userInfo.get("phone"));
            user.setCreateTime(new Date());
            user.setHeadImg("");
            String pwd = userInfo.get("pwd");
            /**
             * MD5加密
             */
            user.setPwd(CommonUtils.MD5(pwd));
            return user;
        }else {
            return null;
        }

    }

    @Override
    public User findByName(String name) {
        User user = userMapper.findByName(name);
        return user;
    }


}
