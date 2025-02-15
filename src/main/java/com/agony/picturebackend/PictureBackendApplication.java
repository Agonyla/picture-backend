package com.agony.picturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.agony.picturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class PictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureBackendApplication.class, args);


        // todo 自定义异常 1. 错误代码(ErrorCode) 2. 响应包装类 3. 全局异常处理器 4. 请求包装类 5. 全局跨域配置

    }

}
