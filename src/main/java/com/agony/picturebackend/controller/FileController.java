package com.agony.picturebackend.controller;

import com.agony.picturebackend.annotation.AuthCheck;
import com.agony.picturebackend.common.BaseResponse;
import com.agony.picturebackend.common.ResultUtils;
import com.agony.picturebackend.constant.UserConstant;
import com.agony.picturebackend.exception.BusinessException;
import com.agony.picturebackend.exception.ErrorCode;
import com.agony.picturebackend.manager.CosManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author: Agony
 * @create: 2025/2/27 9:20
 * @describe: 文件模块
 */

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {


    @Resource
    private CosManager cosManager;

    /**
     * @param multipartFile 文件上传接口
     * @return 返回可访问的文件地址
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @PostMapping("/test/upload")
    public BaseResponse<String> testUploadFile(@RequestPart("file") MultipartFile multipartFile) {

        // MultipartFile 提供了一些常用的方法，比如：
        //
        // getName()：返回文件字段的名称。
        // getOriginalFilename()：返回上传文件的原始文件名。
        // getSize()：返回文件的大小（以字节为单位）。
        // getBytes()：返回文件的字节内容。
        // transferTo(File dest)：将文件的内容写入到指定的文件


        // 文件目录
        String filename = multipartFile.getOriginalFilename();
        String filePath = String.format("/test/%s", filename);

        File file = null;
        try {
            file = File.createTempFile(filePath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filePath, file);
            // 返回可访问的文件地址
            return ResultUtils.success(filePath);

        } catch (Exception e) {

            log.error("file upload error, filepath = {}", filePath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
        } finally {
            if (file != null) {
                // 删除临时文件
                boolean delete = file.delete();
                if (!delete) {
                    log.error("file delete error, filepath = {}", filePath);
                }
            }
        }
    }
}
