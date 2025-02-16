package com.agony.picturebackend.controller;

import com.agony.picturebackend.common.BaseResponse;
import com.agony.picturebackend.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
