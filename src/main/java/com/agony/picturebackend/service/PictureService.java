package com.agony.picturebackend.service;

import com.agony.picturebackend.model.dto.picture.PictureQueryRequest;
import com.agony.picturebackend.model.dto.picture.PictureReviewRequest;
import com.agony.picturebackend.model.dto.picture.PictureUploadRequest;
import com.agony.picturebackend.model.entity.Picture;
import com.agony.picturebackend.model.entity.User;
import com.agony.picturebackend.model.vo.PictureVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Agony
 * @description 针对表【picture(图片)】的数据库操作Service
 * @createDate 2025-02-27 21:25:22
 */
public interface PictureService extends IService<Picture> {


    /**
     * 校验图片
     *
     * @param picture 图片
     */
    void validPicture(Picture picture);

    /**
     * 上传图片
     *
     * @param multipartFile        文件
     * @param pictureUploadRequest 图片上传请求
     * @param loginUser            登录用户
     * @return 图片视图
     */
    PictureVO uploadPicture(MultipartFile multipartFile, PictureUploadRequest pictureUploadRequest, User loginUser);


    /**
     * 图片审核
     *
     * @param pictureReviewRequest 图片审核请求
     * @param loginUser            登录用户
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);


    /**
     * 填充审核参数
     *
     * @param picture   图片
     * @param loginUser 登录用户
     */
    void fillReviewParams(Picture picture, User loginUser);

    /**
     * 获取查询对象
     *
     * @param pictureQueryRequest 图片查询请求
     * @return qw封装类
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);


    /**
     * 获取图片包装类（单条）
     *
     * @param picture 图片
     * @param request http请求
     * @return 图片视图
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 获取图片包装类（分页）
     *
     * @param picturePage 图片分页
     * @param request     http请求
     * @return 图片视图分页
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);
}
