package com.agony.picturebackend.exception;

/**
 * @author: Agony
 * @create: 2025/2/16 14:02
 * @describe: 异常封装类
 */
public class ThrowUtils {


    /**
     * 条件成立则抛出异常
     *
     * @param condition 条件
     * @param e         异常
     */
    public static void throwIf(boolean condition, RuntimeException e) {
        if (condition) {
            throw e;
        }
    }


    /**
     * 条件成立则抛出异常
     *
     * @param condition 条件
     * @param errorCode 错误码
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }


    /**
     * 条件成立则抛出异常
     *
     * @param condition 条件
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }


}
