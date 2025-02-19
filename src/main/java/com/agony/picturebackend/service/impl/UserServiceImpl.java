package com.agony.picturebackend.service.impl;

import com.agony.picturebackend.mapper.UserMapper;
import com.agony.picturebackend.model.entity.User;
import com.agony.picturebackend.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author agony
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-02-19 21:19:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




