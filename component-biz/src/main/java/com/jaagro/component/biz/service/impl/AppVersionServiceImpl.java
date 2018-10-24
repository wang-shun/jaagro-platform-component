package com.jaagro.component.biz.service.impl;

import com.jaagro.component.api.dto.AppVersionDto;
import com.jaagro.component.api.service.AppVersionService;
import com.jaagro.component.biz.entity.AppVersion;
import com.jaagro.component.biz.mapper.AppVersionMapperExt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppVersionServiceImpl implements AppVersionService {
    @Autowired
    private AppVersionMapperExt appVersionMapper;

    /**
     * 获取最新的版本号
     * @author @Gao.
     * @return
     */
    @Override
    public AppVersionDto getLatestVersion() {
        AppVersionDto appVersionDto = new AppVersionDto();
        AppVersion latestVersion = appVersionMapper.getLatestVersion();
        BeanUtils.copyProperties(latestVersion,appVersionDto);
        return appVersionDto;
    }
    /**
     * 插入最新的版本号
     * @author @Gao.
     * @return
     */
    @Override
    public void insertVersion(AppVersionDto appVersion) {
        AppVersion version = new AppVersion();
        BeanUtils.copyProperties(appVersion,version);
        appVersionMapper.insertVersion(version);
    }
}
