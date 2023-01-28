package com.gaokd.online_education.controller;

import com.gaokd.online_education.model.entity.User;
import com.gaokd.online_education.model.request.LoginRequest;
import com.gaokd.online_education.service.UserService;
import com.gaokd.online_education.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public JsonData userLogin(@RequestBody Map<String,String> userInfo){
        int rows = userService.save(userInfo);
        return rows == 1 ? JsonData.buildSucess():JsonData.buildError("注册失败，请重试");
    }

//    @PostMapping("find")
//    public JsonData pwd(@RequestBody User user){
//        String str = userService.findByName(user.getName()).getPwd();
//        System.out.println(str.equals("123456"));
//        return JsonData.buildSucess(userService.findByName(user.getName()));
//    }

    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());
        return token == null ? JsonData.buildError("登陆失败，账号密码错误"):JsonData.buildSucess(token);
    }


    /**
     *根据用户id查询用户信息
     */
    @GetMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        if (userId == null){
            return JsonData.buildError("查询失败");
        }
        User user = userService.findByUserId(userId);
        return JsonData.buildSucess(user);
    }

}
