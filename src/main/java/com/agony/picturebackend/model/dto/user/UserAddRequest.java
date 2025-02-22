package com.agony.picturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/2/22 14:52
 * @describe: 用户添加请求
 */

@Data
public class UserAddRequest implements Serializable {


    private static final long serialVersionUID = -8927919160537428191L;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色: user, admin
     */
    private String userRole;

}


