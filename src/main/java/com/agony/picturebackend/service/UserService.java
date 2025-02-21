package com.agony.picturebackend.service;

import com.agony.picturebackend.model.entity.User;
import com.agony.picturebackend.model.vo.LoginUserVO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author agony
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2025-02-19 21:19:19
 */
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userAccount   用户账号
     * @param userPassword  用户密码
     * @param checkPassword 确认密码
     * @return 返回用户注册后生成的 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);


    /**
     * 用户登录
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @param request      http请求
     * @return 脱敏后的登录用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * 获取脱敏后的登录用户信息
     *
     * @param user 脱敏前的用户
     * @return 脱敏后的登录用户信息
     */
    LoginUserVO getLoginUserVO(User user);


    /**
     * 获取当前登录用户
     *
     * @param request http请求
     * @return 当前登录用户信息
     */
    User getLoginUser(HttpServletRequest request);


    /**
     * 用户注销
     *
     * @param request http请求http请求
     * @return 是否注销成功
     */
    boolean userLogout(HttpServletRequest request);


    /**
     * 获取加密后的密码
     *
     * @param userPassword 用户密码
     * @return 加密后的密码
     */
    String getEncryptPassword(String userPassword);
}
