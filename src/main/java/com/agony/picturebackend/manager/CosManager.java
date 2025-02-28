package com.agony.picturebackend.manager;

import com.agony.picturebackend.config.CosClientConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.ciModel.persistence.PicOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author: Agony
 * @create: 2025/2/27 9:15
 * @describe: 通用的对象存储操作
 */


@Component
public class CosManager {

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private COSClient cosClient;


    /**
     * 上传对象 (附带图片信息)
     *
     * @param key  唯一值
     * @param file 文件
     * @return
     */
    public PutObjectResult putPictureObject(String key, File file) {

        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, file);

        // 对图片进行操作（获取基本信息也被是为一种操作）
        PicOperations picOperations = new PicOperations();

        // 1 表示返回原图信息
        picOperations.setIsPicInfo(1);

        putObjectRequest.setPicOperations(picOperations);

        return cosClient.putObject(putObjectRequest);
    }


    /**
     * 上传对象
     *
     * @param key  唯一值
     * @param file 文件
     * @return
     */
    public PutObjectResult putObject(String key, File file) {

        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, file);

        return cosClient.putObject(putObjectRequest);
    }


    /**
     * 下载对象
     *
     * @param key 唯一值
     * @return
     */
    public COSObject getObject(String key) {

        GetObjectRequest getObjectRequest = new GetObjectRequest(cosClientConfig.getBucket(), key);
        return cosClient.getObject(getObjectRequest);
    }


}
