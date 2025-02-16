package com.agony.picturebackend.common;

import com.agony.picturebackend.exception.ErrorCode;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Agony
 * @create: 2025/2/15 17:21
 * @describe: 响应包装类
 */
@Data
@Accessors(chain = true)
public class BaseResponse<T> {


    private int code;

    private String message;

    private T data;

    private long timestamp;

    // public BaseResponse() {
    //     this.timestamp = System.currentTimeMillis();
    // }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public BaseResponse(int code, T data) {
        this(code, "", data);
    }


    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage(), null);
    }
}
