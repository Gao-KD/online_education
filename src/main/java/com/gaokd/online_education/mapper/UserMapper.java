package com.gaokd.online_education.mapper;

import com.gaokd.online_education.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByName(@Param("name") String name);

    int save(User user);

    User findByPhoneAndPwd(@Param("phone") String phone,@Param("pwd") String pwd);

    User findByUserId(@Param("user_id")Integer userId);
}
