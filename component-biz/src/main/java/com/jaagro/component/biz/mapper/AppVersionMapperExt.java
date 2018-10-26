package com.jaagro.component.biz.mapper;

import com.jaagro.component.biz.entity.AppVersion;
/**
 * @author @Gao.
 */
public interface AppVersionMapperExt {
    /**
     * 获取最新一条版本号信息
     */
    AppVersion getLatestVersion();
    /**
     * 插入最新版本信息
     */
    void insertVersion(AppVersion appVersion);
}
