package com.jaagro.component.api.service;

import com.jaagro.component.api.dto.AppVersionDto;

public interface AppVersionService {

    /**
     * 获取最新一条版本号信息
     */
    AppVersionDto getLatestVersion();
    /**
     * 插入最新版本信息
     */
    void insertVersion(AppVersionDto appVersion);

}
