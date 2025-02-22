package com.agony.picturebackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/2/22 14:53
 * @describe: 用户更新请求
 */

@Data
public class UserUpdateRequest implements Serializable {

    private static final long serialVersionUID = -5205780942112718225L;


    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

}


