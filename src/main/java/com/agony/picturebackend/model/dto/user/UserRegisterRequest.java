package com.agony.picturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/2/19 21:37
 * @describe: 用户注册请求
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -5125961225426576700L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
