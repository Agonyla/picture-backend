package com.agony.picturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/3/6 20:56
 * @describe:
 */


@Data
public class PictureReviewRequest implements Serializable {


    private static final long serialVersionUID = 2603434494307959078L;

    /**
     * id
     */
    private Long id;

    /**
     * 审核状态：0-待审核; 1-通过; 2-拒绝
     */
    private Integer reviewStatus;

    /**
     * 审核信息
     */
    private String reviewMessage;


}
