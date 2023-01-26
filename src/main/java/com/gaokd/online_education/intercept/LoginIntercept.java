package com.gaokd.online_education.intercept;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaokd.online_education.utils.JWTUtils;
import com.gaokd.online_education.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginIntercept implements HandlerInterceptor {
    /**
     * 进入到controller之前的方法,登录拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            String accessToken = request.getHeader("token");
            if (accessToken==null){
                request.getParameter("token");
            }
            if (StringUtils.isNotBlank(accessToken)){
                //token解密
                Claims claims = JWTUtils.checkJWT(accessToken);
                if (claims == null){
                    //登录过期，重新登录
                    sendJsonMessage(response, JsonData.buildError("登录过期，重新登陆"));
                    return false;
                }else {
                    //获取id和name并赋值给前端
                    Integer id = (Integer) claims.get("id");
                    String name = (String) claims.get("name");
                    request.setAttribute("user_id",id);
                    request.setAttribute("user_name",name);
                    return true;
                }
            }
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }catch (Exception e){
            e.printStackTrace();
            sendJsonMessage(response, JsonData.buildError("登录过期，重新登陆"));
            return false;
        }
    }

    public static void sendJsonMessage(HttpServletResponse response,Object obj){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
