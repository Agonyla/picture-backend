package com.agony.picturebackend.aop;

import com.agony.picturebackend.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: Agony
 * @create: 2025/2/28 14:42
 * @describe: 四种切面通知
 */

@Aspect
@Component
@Slf4j
public class HealthInterceptor {

    // 执行顺序
    // Before → 执行业务方法 → (如果成功) AfterReturning → After
    //                     → (如果失败) AfterThrowing → After

    // 定义切点（匹配 HealthController 中的 add 方法）
    @Pointcut(value = "execution(* com.agony.picturebackend.controller.HealthController.add(int, int)) && args(a, b)", argNames = "a,b")
    public void addMethodPointcut(int a, int b) {
    }

    // @Before(value = "execution(public  com.agony.picturebackend.controller.HealthController.add(..))")
    @Before("execution(* com.agony.picturebackend.controller.HealthController.add(int, int))")
    public void beforeAdd(JoinPoint joinPoint) {
        log.info("[Before] 方法 {} 被调用，参数：{}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }


    @AfterReturning(value = "addMethodPointcut(a,b)", returning = "result", argNames = "joinPoint,a,b,result")
    public void afterReturningAdd(JoinPoint joinPoint, int a, int b, BaseResponse<Integer> result) {
        log.info("[AfterReturning] 计算结果: {}", result.getData());
    }


    @AfterThrowing(pointcut = "addMethodPointcut(a, b)", throwing = "ex", argNames = "joinPoint,a,b,ex")
    public void afterThrowingAdd(JoinPoint joinPoint, int a, int b, Exception ex) {
        log.error("[AfterThrowing] 计算发生异常，参数 a={}, b={}，错误信息: {}", a, b, ex.getMessage());
    }


    @After(value = "addMethodPointcut(a, b)", argNames = "joinPoint,a,b")
    public void afterAdd(JoinPoint joinPoint, int a, int b) {
        log.info("[After] 方法调用完成，参数 a={}, b={}", a, b);
    }
}
