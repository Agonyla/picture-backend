package com.agony.picturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.agony.picturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class PictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureBackendApplication.class, args);


        // todo 自定义异常 1. 错误代码(ErrorCode) 2. 响应包装类 3. 全局异常处理器 4. 请求包装类 5. 全局跨域配置


        // todo User类可以加一个 @Accessors(chain = true) 注解

        // todo 在写UserService的业务逻辑时，不要用 throw new BusinessException, 试着用 ThrowUtils.throwIf()


        // todo 关于后端模板设计模式那里，yupi是直接使用了Object，是否可以改成泛型

        // todo spaceService里面的校验试一下用 注解@Max @Null等等能不能生效

        // todo imageSearch 的时候 用bing提供的api和 抓取请求url的试一下
    }

}
