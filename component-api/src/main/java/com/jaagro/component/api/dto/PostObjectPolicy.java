package com.jaagro.component.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tony
 */
@Data
@Accessors(chain = true)
public class PostObjectPolicy implements Serializable {
    private String accessId;
    private String accessKeySecret;
    private String host;
    private String dir;
    private String policy;
    private String expire;
    private String signature;
}
