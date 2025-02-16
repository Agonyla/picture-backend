package com.agony.picturebackend.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: Agony
 * @create: 2025/2/16 14:56
 * @describe: 分页请求包装类
 */

@Data
@Accessors(chain = true)
public class PageRequest {

    /**
     * 当前页码
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;


    /**
     * 排序字段
     */
    private String sortField;


    /**
     * 排序顺序 (默认降序)
     */
    private String sortOrder = "descend";


}

