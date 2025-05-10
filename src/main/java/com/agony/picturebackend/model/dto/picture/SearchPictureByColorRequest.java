package com.agony.picturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/5/10 20:29
 * @describe: 按照颜色搜索图片请求
 */
@Data
public class SearchPictureByColorRequest implements Serializable {

    /**
     * 图片主色调
     */
    private String picColor;

    /**
     * 空间 id
     */
    private Long spaceId;

    private static final long serialVersionUID = 1L;
}
