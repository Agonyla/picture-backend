package com.agony.picturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: Agony
 * @create: 2025/3/2 20:38
 * @describe: 图片更新请求
 */
@Data
public class PictureUpdateRequest implements Serializable {

    private static final long serialVersionUID = 381334941480507765L;


    /**
     * id
     */
    private Long id;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 分类
     */
    private String category;

    /**
     * 标签
     */
    private List<String> tags;

}
