package com.jaagro.component.web.controller;

import com.jaagro.component.api.dto.PostObjectPolicy;
import com.jaagro.component.api.service.OssService;
import com.jaagro.utils.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 */
@RestController
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "批量获取oss上传签名")
    @PostMapping("/listOssPolicy")
    public BaseResponse listOssPolicy(@RequestParam String packageName, @RequestParam int size){
        if (size == 0) {
            return BaseResponse.errorInstance("size参数不能小于0");
        }

        //文件子目录 按照当前日期
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String fileDir = df.format(new Date());
        List<PostObjectPolicy> policies = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            PostObjectPolicy policy = ossService.getPostObjectPolicy(packageName + "/" + fileDir,300);
            policies.add(policy);
        }
        return BaseResponse.successInstance(policies);
    }

    @ApiOperation(value = "获取oss文件存储路径")
    @PostMapping("/listSignedUrl")
    public List<URL> listSignedUrl(@RequestParam String[] filePath){
        if(filePath.length == 0){
            throw new NullPointerException("文件路径不能为空");
        }
        List<URL> urlList = new ArrayList<>();
         for (int i = 0; i < filePath.length; i ++) {
            urlList.add(ossService.getSignedUrl(filePath[i]));
        }
        return urlList;
    }

    @ApiOperation(value = "获取Oss STS token")
    @PostMapping("/getOssSts")
    public BaseResponse getSTSToken() {
        Map<String, Object> stsToken = ossService.generateStsToken();
        BaseResponse response = new BaseResponse();
        if (stsToken != null) {
            response.setData(stsToken);
        } else {
            return BaseResponse.errorInstance("服务器无法访问！");
        }
        return response;
    }

}
