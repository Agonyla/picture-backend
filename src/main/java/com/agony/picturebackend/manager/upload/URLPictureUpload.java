package com.agony.picturebackend.manager.upload;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.agony.picturebackend.exception.BusinessException;
import com.agony.picturebackend.exception.ErrorCode;
import com.agony.picturebackend.exception.ThrowUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/3/9 21:11
 * @describe: URL上传
 */

@Service
public class URLPictureUpload extends PictureUploadTemplate {


    @Override
    protected void validPicture(Object inputSource) {

        String fileURL = (String) inputSource;

        ThrowUtils.throwIf(StrUtil.isBlank(fileURL), ErrorCode.PARAMS_ERROR, "图片地址为空");

        // 1. 校验url格式
        try {
            new URL(fileURL);
        } catch (MalformedURLException e) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "图片地址格式不正确");
        }

        // 2. 校验URL协议
        ThrowUtils.throwIf(!(fileURL.startsWith("http://") || fileURL.startsWith("https://")), ErrorCode.PARAMS_ERROR, "仅支持 HTTP 或 HTTPS 协议的地址文件");

        // 3. 发送 HEAD 请求以验证文件是否存在

        try (HttpResponse response = HttpUtil.createRequest(Method.HEAD, fileURL).execute()) {
            if (response.getStatus() != HttpStatus.HTTP_OK) {
                return;
            }
            // 4. 校验文件类型
            String contentType = response.header("Content-type");
            if (StrUtil.isNotBlank(contentType)) {
                final List<String> ALLOW_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png", "image/webp");
                ThrowUtils.throwIf(!ALLOW_CONTENT_TYPES.contains(contentType), ErrorCode.PARAMS_ERROR, "文件类型错误");
            }

            // 5. 校验文件大小
            String contentLength = response.header("Content-Length");
            if (StrUtil.isNotBlank(contentLength)) {

                long length = Long.parseLong(contentLength);
                final long TWO_MB = 2 * 1024 * 1024L; // 限制文件大小为 2MB
                ThrowUtils.throwIf(length > TWO_MB, ErrorCode.PARAMS_ERROR, "文件大小不能超过 2MB");
            }
        }
    }

    @Override
    protected String getOriginFileName(Object inputSource) {
        String fileURL = (String) inputSource;
        return FileUtil.mainName(fileURL);
    }

    @Override
    protected void processFile(Object inputSource, File file) throws IOException {
        String fileURL = (String) inputSource;
        HttpUtil.downloadFile(fileURL, file);
    }
}
