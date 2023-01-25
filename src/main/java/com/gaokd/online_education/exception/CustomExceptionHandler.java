package com.gaokd.online_education.exception;

import com.gaokd.online_education.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(Exception e){
        logger.error("系统错误[]"+e);
        if (e instanceof OException){
            OException oException = (OException) e;
            return JsonData.buildError(oException.getCode(), oException.getMsg());
        }
        else {
            return JsonData.buildError("全局异常，未知错误");
        }
    }
}
