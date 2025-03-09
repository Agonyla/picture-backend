package com.agony.picturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/2/27 21:28
 * @describe: 图片上传请求
 */
@Data
public class PictureUploadRequest implements Serializable {


    private static final long serialVersionUID = -8000588394943438406L;


    /**
     * 图片id （用于修改）
     */
    private Long id;


    /**
     * 文件地址
     */
    private String fileURL;


    /**
     * 图片名称
     */
    private String picName;


}
