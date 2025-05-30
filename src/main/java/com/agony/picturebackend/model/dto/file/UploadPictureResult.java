package com.agony.picturebackend.model.dto.file;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/2/28 20:17
 * @describe: 接受图片解析信息的包装类
 */


@Data
public class UploadPictureResult implements Serializable {


    private static final long serialVersionUID = -3452385465906430376L;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 缩略图 url
     */
    private String thumbnailUrl;


    /**
     * 图片名称
     */
    private String picName;

    /**
     * 文件体积
     */
    private Long picSize;

    /**
     * 图片宽度
     */
    private Integer picWidth;

    /**
     * 图片高度
     */
    private Integer picHeight;

    /**
     * 图片宽高比
     */
    private Double picScale;

    /**
     * 图片格式
     */
    private String picFormat;

    /**
     * 图片主色调
     */
    private String picColor;


}
