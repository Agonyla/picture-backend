package com.agony.picturebackend.mapper;

import com.agony.picturebackend.common.BaseResponse;
import com.agony.picturebackend.exception.ErrorCode;

/**
 * @author: Agony
 * @create: 2025/2/15 19:57
 * @describe: 测试
 */
public class Main {


    public static void main(String[] args) {


        BaseResponse<String> hello = new BaseResponse<>(123, "Hello");
        System.out.println(hello);


        BaseResponse<String> stringBaseResponse = new BaseResponse<>(ErrorCode.NO_AUTH_ERROR);
        System.out.println(stringBaseResponse);
    }
}
