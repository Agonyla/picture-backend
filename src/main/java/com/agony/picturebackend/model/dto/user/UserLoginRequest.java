package com.agony.picturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/2/19 21:37
 * @describe: 用户登录请求
 */
@Data
public class UserLoginRequest implements Serializable {


    private static final long serialVersionUID = -977285719889907394L;


    private String userAccount;

    private String userPassword;

}
