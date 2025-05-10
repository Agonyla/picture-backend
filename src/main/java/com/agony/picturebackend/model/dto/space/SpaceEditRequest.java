package com.agony.picturebackend.model.dto.space;


import lombok.Data;

import java.io.Serializable;


/**
 * @author: Agony
 * @create: 2025/5/10 19:33
 * @describe: 编辑空间请求
 */
@Data
public class SpaceEditRequest implements Serializable {


    /**
     * 空间 id
     */
    private Long id;


    /**
     * 空间名称
     */
    private String spaceName;
    

    private static final long serialVersionUID = 1L;
}
