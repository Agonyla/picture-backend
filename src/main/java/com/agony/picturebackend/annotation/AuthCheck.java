package com.agony.picturebackend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Agony
 * @create: 2025/2/21 15:16
 * @describe: 权限校验接口
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 必须要某个角色
     */
    String mustRole() default "";


}
