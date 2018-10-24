package com.jaagro.component.web.controller;

import com.jaagro.component.api.dto.AppVersionDto;
import com.jaagro.component.api.service.AppVersionService;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author @Gao.
 */
@RestController
@Api(description = "App版本管理", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppVersionController {
    @Autowired
    private AppVersionService appVersionService;

    @ApiOperation("获取App最新版本号")
    @GetMapping("/getLatestVersionApp")
    public BaseResponse getLatestVersionApp() {
        AppVersionDto latestVersion = appVersionService.getLatestVersion();
        return BaseResponse.successInstance(latestVersion);
    }

    @ApiOperation("新增App版本号")
    @PostMapping("/insertVersionApp")
    public BaseResponse insertVersionApp(@RequestBody AppVersionDto dto) {
        appVersionService.insertVersion(dto);
        return BaseResponse.successInstance(ResponseStatusCode.OPERATION_SUCCESS);
    }

}
