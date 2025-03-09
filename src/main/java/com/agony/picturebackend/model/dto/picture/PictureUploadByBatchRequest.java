package com.agony.picturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/3/9 21:38
 * @describe: 图片抓取请求
 */

@Data
public class PictureUploadByBatchRequest implements Serializable {


    private static final long serialVersionUID = 1759964847175918566L;

    /**
     * 搜索词
     */
    private String searchText;


    /**
     * 图片名称前缀
     */
    private String namePrefix;

    /**
     * 抓取数量
     */
    private Integer count = 10;


}
