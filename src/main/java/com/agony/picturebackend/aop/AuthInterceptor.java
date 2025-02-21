package com.agony.picturebackend.aop;

import com.agony.picturebackend.annotation.AuthCheck;
import com.agony.picturebackend.model.entity.User;
import com.agony.picturebackend.model.enums.UserRoleEnum;
import com.agony.picturebackend.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: Agony
 * @create: 2025/2/17 18:32
 * @describe: 校验权限aop
 */
@Aspect
@Component
public class AuthInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);


    // todo 在写aop前面的时候调试一下 String mustRole = authCheck.mustRole()，看一下这个mustRole


    @Resource
    private UserService userService;


    /**
     * 执行用户权限校验拦截
     *
     * @param joinPoint 切入点
     * @param authCheck 权限校验注解
     * @return
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {


        String mustRole = authCheck.mustRole;
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 当前登录用户
        User loginUser = userService.getLoginUser(request);
        String userRole = loginUser.getUserRole();
        // 接口权限要求枚举类
        UserRoleEnum mustRoleEnum = UserRoleEnum.getUserEnumByValue(mustRole);
        // 用户权限枚举类
        UserRoleEnum userRoleEnum = UserRoleEnum.getUserEnumByValue(userRole);

        // 如果当前请求对用户权限没有要求
        if (mustRoleEnum == null) {
            joinPoint.proceed();
        }

        // region 以下为：必须有该权限才能通过


        // todo 校验权限
        // endregion

        return null;
    }

}
