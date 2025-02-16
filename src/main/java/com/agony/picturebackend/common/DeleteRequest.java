package com.agony.picturebackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Agony
 * @create: 2025/2/16 16:26
 * @describe: 删除请求包装类
 */

@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;

}
