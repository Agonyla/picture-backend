package com.agony.picturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/5/10 20:29
 * @describe: 以图搜图请求
 */
@Data
public class SearchPictureByPictureRequest implements Serializable {

    /**
     * 图片 id
     */
    private Long pictureId;

    private static final long serialVersionUID = 1L;
}
