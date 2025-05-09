package com.agony.picturebackend.manager.upload;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.agony.picturebackend.config.CosClientConfig;
import com.agony.picturebackend.exception.BusinessException;
import com.agony.picturebackend.exception.ErrorCode;
import com.agony.picturebackend.manager.CosManager;
import com.agony.picturebackend.model.dto.file.UploadPictureResult;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.ciModel.persistence.CIObject;
import com.qcloud.cos.model.ciModel.persistence.ImageInfo;
import com.qcloud.cos.model.ciModel.persistence.ProcessResults;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/3/9 20:32
 * @describe: 图片上传抽象模板
 */


@Slf4j
public abstract class PictureUploadTemplate {

    @Resource
    private CosManager cosManager;

    @Resource
    private CosClientConfig cosClientConfig;


    public final UploadPictureResult uploadPicture(Object inputSource, String uploadPathPrefix) {

        // 1. 检验文件
        validPicture(inputSource);

        // 2. 生成图片上传地址 地址前缀 + 文件名（日期 + 随机16位长度的字符串 + 文件后缀名）
        String uuid = RandomUtil.randomString(16);
        String originalFilename = getOriginFileName(inputSource);
        String uploadFilename = String.format("%s_%s.%s", DateUtil.formatDate(new Date()), uuid, FileUtil.getSuffix(originalFilename));
        String uploadPath = String.format("/%s/%s", uploadPathPrefix, uploadFilename);

        // 3. 上传文件
        File file = null;
        try {
            // 创建临时文件
            file = File.createTempFile(uploadPath, null);

            // 处理文件来源 本地/URL
            processFile(inputSource, file);

            // 上传文件对象
            PutObjectResult putObjectResult = cosManager.putPictureObject(uploadPath, file);

            // todo
            // 获取图片信息对象
            ImageInfo imageInfo = putObjectResult.getCiUploadResult().getOriginalInfo().getImageInfo();

            // 获取到图片处理结果
            ProcessResults processResults = putObjectResult.getCiUploadResult().getProcessResults();
            List<CIObject> objectList = processResults.getObjectList();
            if (CollectionUtil.isNotEmpty(objectList)) {
                CIObject compressCIObject = objectList.get(0);
                // 缩略图默认等于压缩图
                CIObject thumbnailCiObject = compressCIObject;
                // 有生成缩略图，才获取缩略图
                if (objectList.size() > 1) {
                    thumbnailCiObject = objectList.get(1);
                }
                // 封装压缩图的返回结果
                return buildResult(originalFilename, compressCIObject, thumbnailCiObject);
            }

            // 返回可访问的地址
            return buildResult(imageInfo, uploadPath, originalFilename, file);

        } catch (Exception e) {
            log.error("图片上传到对象存储失败", e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "图片上传失败");
        } finally {
            // 临时文件清理
            this.deleteTempFile(file);
        }
    }


    /**
     * 校验输入源（本地文件或 URL）
     *
     * @param inputSource 本地文件或 URL
     */
    protected abstract void validPicture(Object inputSource);

    /**
     * 获取输入源的原始文件名
     *
     * @param inputSource 本地文件或 URL
     * @return 原始文件名
     */
    protected abstract String getOriginFileName(Object inputSource);


    /**
     * 处理输入源并生成本地临时文件
     *
     * @param inputSource 本地文件或 URL
     * @param file        文件
     */
    protected abstract void processFile(Object inputSource, File file) throws IOException;


    /**
     * 封装返回结果
     *
     * @param originFilename
     * @param compressedCIObject
     * @return
     */
    private UploadPictureResult buildResult(String originFilename, CIObject compressedCIObject, CIObject thumbnailCiObject) {

        // 计算宽高
        int picWidth = compressedCIObject.getWidth();
        int picHeight = compressedCIObject.getHeight();
        double picScale = NumberUtil.round(picWidth * 1.0 / picHeight, 2).doubleValue();

        // 封装返回结果
        UploadPictureResult uploadPictureResult = new UploadPictureResult();

        // 设置压缩后的原图地址
        uploadPictureResult.setUrl(cosClientConfig.getHost() + "/" + compressedCIObject.getKey());
        uploadPictureResult.setPicName(FileUtil.mainName(originFilename));
        uploadPictureResult.setPicWidth(picWidth);
        uploadPictureResult.setPicHeight(picHeight);
        uploadPictureResult.setPicScale(picScale);
        uploadPictureResult.setPicFormat(compressedCIObject.getFormat());
        uploadPictureResult.setPicSize(compressedCIObject.getSize().longValue());

        // 设置缩略图地址
        uploadPictureResult.setThumbnailUrl(cosClientConfig.getHost() + "/" + thumbnailCiObject.getKey());

        // 返回可访问地址
        return uploadPictureResult;
    }


    /**
     * 封装返回结果
     *
     * @param originalFilename
     * @param file
     * @param uploadPath
     * @param imageInfo        对象存储返回的图片信息
     * @return
     */
    private UploadPictureResult buildResult(ImageInfo imageInfo, String uploadPath, String originalFilename, File file) {

        // 计算宽高
        int picWidth = imageInfo.getWidth();
        int picHeight = imageInfo.getHeight();
        double picScale = NumberUtil.round(picWidth * 1.0 / picHeight, 2).doubleValue();

        // 封装返回结果
        UploadPictureResult uploadPictureResult = new UploadPictureResult();
        uploadPictureResult.setUrl(cosClientConfig.getHost() + "/" + uploadPath);
        uploadPictureResult.setPicName(FileUtil.mainName(originalFilename));
        uploadPictureResult.setPicSize(FileUtil.size(file));
        uploadPictureResult.setPicWidth(picWidth);
        uploadPictureResult.setPicHeight(picHeight);
        uploadPictureResult.setPicScale(picScale);
        uploadPictureResult.setPicFormat(imageInfo.getFormat());

        // 返回可访问地址
        return uploadPictureResult;
    }

    /**
     * 删除临时文件
     *
     * @param file 临时文件
     */
    private void deleteTempFile(File file) {
        if (file == null) {
            return;
        }
        // 删除临时文件
        boolean deleteResult = file.delete();
        if (!deleteResult) {
            log.error("file delete error, filepath = {}", file.getAbsolutePath());
        }
    }

}
