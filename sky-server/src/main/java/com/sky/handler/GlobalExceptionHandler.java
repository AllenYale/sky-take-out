package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * ////捕获所有继承自BaseException的异常，并添加异常msg，封装到Result对象后返回json给前端
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * SQLIntegrityConstraintViolationException 统一异常处理
     * @param exception
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException exception) {
        //Duplicate entry 'xxx' for key 'xxxx'
        String msg = exception.getMessage();
        if (msg.contains("Duplicate entr")) {
            String duplicationStr = msg.split(" ")[2];
            String resultMsg = duplicationStr + MessageConstant.ALREADY_EXIST;
            return Result.error(resultMsg);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
