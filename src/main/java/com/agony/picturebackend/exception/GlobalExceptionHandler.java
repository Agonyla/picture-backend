package com.agony.picturebackend.exception;

import com.agony.picturebackend.common.BaseResponse;
import com.agony.picturebackend.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: Agony
 * @create: 2025/2/16 16:22
 * @describe: 全局异常处理器
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {

        log.error("BusinessException: {}", e.getMessage());
        return ResultUtils.error(e.getCode(), e.getMessage());

    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {

        log.error("RuntimeException: ", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");

    }


}
