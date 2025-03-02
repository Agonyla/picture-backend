package com.agony.picturebackend.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: Agony
 * @create: 2025/3/2 21:19
 * @describe: 图片标签分类列表视图
 */
@Data
public class PictureTagCategory {


    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 分类列表
     */
    private List<String> categoryList;
}
