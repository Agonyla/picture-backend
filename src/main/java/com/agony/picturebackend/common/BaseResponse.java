package com.agony.picturebackend.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Agony
 * @create: 2025/2/15 17:21
 * @describe:
 */
@Data
@Accessors(chain = true)
public class BaseResponse<T> {

    // todo 加一个时间戳字段  private long timestamp;


    private int code;
    private String message;
    private T data;
    private long timestamp;


}
