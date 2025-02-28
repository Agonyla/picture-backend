package com.agony.picturebackend.controller;

import com.agony.picturebackend.common.BaseResponse;
import com.agony.picturebackend.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Agony
 * @create: 2025/2/15 20:45
 * @describe:
 */

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping("/health")
    public BaseResponse<String> health() {
        return ResultUtils.success("ok");
    }

    /**
     * // @RequestParam 加了这个注解要求请求参数必须传递
     *
     * @param a 参数1
     * @param b 参数2
     * @return 返回2
     */
    @GetMapping("/add")
    public BaseResponse<Integer> add(@RequestParam int a, @RequestParam int b) {
        return ResultUtils.success(a + b);
    }


}
