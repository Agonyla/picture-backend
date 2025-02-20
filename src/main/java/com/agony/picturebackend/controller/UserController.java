package com.agony.picturebackend.controller;

import com.agony.picturebackend.common.BaseResponse;
import com.agony.picturebackend.common.ResultUtils;
import com.agony.picturebackend.exception.ErrorCode;
import com.agony.picturebackend.exception.ThrowUtils;
import com.agony.picturebackend.model.dto.user.UserRegisterRequest;
import com.agony.picturebackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Agony
 * @create: 2025/2/15 20:45
 * @describe:
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;


    @PostMapping("/register")
    public BaseResponse<Long> userRegister(UserRegisterRequest userRegisterRequest) {

        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR, "请求参数为空");
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        long id = userService.userRegister(userAccount, userPassword, checkPassword);

        return ResultUtils.success(id);
    }


}
