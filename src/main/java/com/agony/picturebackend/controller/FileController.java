package com.agony.picturebackend.controller;

import com.agony.picturebackend.annotation.AuthCheck;
import com.agony.picturebackend.common.BaseResponse;
import com.agony.picturebackend.common.ResultUtils;
import com.agony.picturebackend.constant.UserConstant;
import com.agony.picturebackend.exception.BusinessException;
import com.agony.picturebackend.exception.ErrorCode;
import com.agony.picturebackend.manager.CosManager;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.utils.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
     * 测试文件上传
     *
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
            PutObjectResult putObjectResult = cosManager.putObject(filePath, file);
            log.info(String.valueOf(putObjectResult));
            // log.info(putObjectResult.getRequestId() + "-----------------------");
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


    /**
     * 下载对象测试
     *
     * @param filePath 文件路径
     * @param response servlet相应
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/test/download")
    public BaseResponse<String> testDownloadFile(String filePath, HttpServletResponse response) throws IOException {

        InputStream cosObjectInput = null;

        try {
            COSObject cosObject = cosManager.getObject(filePath);
            cosObjectInput = cosObject.getObjectContent();

            // 处理下载到的流
            byte[] bytes = IOUtils.toByteArray(cosObjectInput);

            // 设置响应头
            // response.setContentType("application/octet-stream;charset=UTF-8");
            // response.setHeader("Content-Disposition", "attachment; filename=" + filePath);
            //
            // // 写入相应
            // response.getOutputStream().write(bytes);
            // response.getOutputStream().flush();

            // 本地路径  filePath 是 /test/xxx.jpg。 所以本地路径必须要有 /test文件夹
            String localFilePath = "D:/Agony/Desktop/downloadTest" + filePath;

            log.info(localFilePath);

            FileOutputStream fos = new FileOutputStream(localFilePath);
            fos.write(bytes);
            return ResultUtils.success("文件保存成功：" + localFilePath);

        } catch (IOException e) {
            log.error("file download error, filePath = {}", filePath, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "文件保存到本地失败");
        } finally {
            if (cosObjectInput != null) {
                cosObjectInput.close();
            }
        }


    }
}
