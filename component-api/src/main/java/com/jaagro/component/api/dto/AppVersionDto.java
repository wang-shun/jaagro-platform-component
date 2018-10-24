package com.jaagro.component.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author @Gao.
 */
@Data
@Accessors(chain = true)
public class AppVersionDto implements Serializable {

    private Integer id;
    /**
     * 版本编码
     */
    private Long versionCode;
    /**
     * 版本号
     */
    private String versionName;
    /**
     * app包名
     */
    private String appPackage;
    /**
     * 是否强制更新
     */
    private Boolean forceUpdate;
    /**
     * 备注
     */
    private String notes;

}
