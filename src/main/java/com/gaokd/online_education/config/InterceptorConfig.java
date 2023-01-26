package com.gaokd.online_education.config;

import com.gaokd.online_education.intercept.LoginIntercept;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * 不用权限可以访问url：/api/v1/pub
 * 要权限才可以访问url：/api/v1/pri
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    LoginIntercept loginIntercept(){
        return new LoginIntercept();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercept())
                //拦截全部
                .addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截哪些,斜杠一定要加
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
