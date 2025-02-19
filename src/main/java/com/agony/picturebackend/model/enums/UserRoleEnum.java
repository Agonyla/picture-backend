package com.agony.picturebackend.model.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author: Agony
 * @create: 2025/2/19 21:25
 * @describe: 用户枚举类
 */
public enum UserRoleEnum {

    USER("用户", "user"),
    ADMIN("管理员", "admin"),
    VIP("会员", "vip");


    private final String text;

    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }


    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值的value
     * @return 枚举值
     */
    public static UserRoleEnum getUserEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }

        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (userRoleEnum.value.equals(value)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
