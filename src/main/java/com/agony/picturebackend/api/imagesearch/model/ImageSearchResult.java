package com.agony.picturebackend.api.imagesearch.model;

import lombok.Data;

/**
 * @author: Agony
 * @create: 2025/5/10 20:18
 * @describe:
 */
@Data
public class ImageSearchResult {

    /**
     * 缩略图地址
     */
    private String thumbUrl;

    /**
     * 来源地址
     */
    private String fromUrl;
}

