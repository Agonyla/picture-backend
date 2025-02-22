package com.agony.picturebackend.controller;

import cn.hutool.core.bean.BeanUtil;
import com.agony.picturebackend.annotation.AuthCheck;
import com.agony.picturebackend.common.BaseResponse;
import com.agony.picturebackend.common.DeleteRequest;
import com.agony.picturebackend.common.ResultUtils;
import com.agony.picturebackend.constant.UserConstant;
import com.agony.picturebackend.exception.BusinessException;
import com.agony.picturebackend.exception.ErrorCode;
import com.agony.picturebackend.exception.ThrowUtils;
import com.agony.picturebackend.model.dto.user.*;
import com.agony.picturebackend.model.entity.User;
import com.agony.picturebackend.model.vo.LoginUserVO;
import com.agony.picturebackend.model.vo.UserVO;
import com.agony.picturebackend.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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


    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求
     * @return 封装后的用户id
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR, "请求参数为空");
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        long id = userService.userRegister(userAccount, userPassword, checkPassword);

        return ResultUtils.success(id);
    }


    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求
     * @param request          http请求
     * @return 脱敏后的用户登录信息
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {

        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR, "请求参数为空");
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);

        return ResultUtils.success(loginUserVO);
    }


    /**
     * 获取当前登录用户
     *
     * @param request http请求
     * @return 脱敏后的当前登录用户
     */
    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {

        User loginUser = userService.getLoginUser(request);
        LoginUserVO loginUserVO = userService.getLoginUserVO(loginUser);


        return ResultUtils.success(loginUserVO);

    }


    /**
     * 用户注销
     *
     * @param request http请求
     * @return 是否注销成功
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {

        boolean b = userService.userLogout(request);

        return ResultUtils.success(b);
    }


    /**
     * 用户添加
     *
     * @param userAddRequest 用户添加请求
     * @return 添加之后创建的用户id
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/add")
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {

        if (userAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        User user = new User();
        BeanUtil.copyProperties(userAddRequest, user);

        // 默认密码
        final String DEFAULT_PASSWORD = "12345678";
        String encryptPassword = userService.getEncryptPassword(DEFAULT_PASSWORD);
        user.setUserPassword(encryptPassword);
        boolean result = userService.save(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, "用户添加失败");

        return ResultUtils.success(user.getId());

    }


    /**
     * 根据id获取用户 （管理员）
     *
     * @param id 用户id
     * @return 用户信息
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/get")
    public BaseResponse<User> getUserById(long id) {

        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);

        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR, "用户信息不存在");

        return ResultUtils.success(user);
    }


    /**
     * 根据id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(long id) {
        BaseResponse<User> res = getUserById(id);
        User user = res.getData();
        UserVO userVO = userService.getUserVO(user);
        return ResultUtils.success(userVO);
    }

    /**
     * 删除用户
     *
     * @param deleteRequest 删除用户请求
     * @return 是否删除成功
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {


        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() <= 0, ErrorCode.PARAMS_ERROR, "删除请求参数错误");
        boolean result = userService.removeById(deleteRequest.getId());
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, "用户删除失败");
        return ResultUtils.success(true);
    }


    /**
     * 更新用户
     *
     * @param userUpdateRequest 更新用户请求
     * @return 是否更新成功
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        ThrowUtils.throwIf(userUpdateRequest == null, ErrorCode.PARAMS_ERROR, "更新请求参数错误");

        User user = new User();
        BeanUtil.copyProperties(userUpdateRequest, user);
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, "用户更新失败");
        return ResultUtils.success(true);
    }


    /**
     * 分页获取用户分装列表
     *
     * @param userQueryRequest 查询请求参数
     * @return 用户分装列表
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest) {

        ThrowUtils.throwIf(userQueryRequest == null, ErrorCode.PARAMS_ERROR, "分页请求参数错误");

        int current = userQueryRequest.getCurrent();
        int pageSize = userQueryRequest.getPageSize();

        Page<User> userPage = userService.page(new Page<>(current, pageSize), userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current, pageSize, userPage.getTotal());
        List<UserVO> userVOList = userService.getUserVOList(userPage.getRecords());
        userVOPage.setRecords(userVOList);

        return ResultUtils.success(userVOPage);
    }
}
