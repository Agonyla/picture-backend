package com.agony.picturebackend.model.dto.space;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Agony
 * @create: 2025/5/10 19:39
 * @describe: 空间等级
 */
@Data
@AllArgsConstructor
public class SpaceLevel {

    /**
     * 值
     */
    private int value;

    /**
     * 中文
     */
    private String text;

    /**
     * 最大数量
     */
    private long maxCount;

    /**
     * 最大容量
     */
    private long maxSize;
}
