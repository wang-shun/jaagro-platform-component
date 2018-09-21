package com.jaagro.component.api.service;

import com.jaagro.component.api.dto.PostObjectPolicy;

import java.net.URL;
import java.util.Map;

/**
 * @author tony
 */
public interface OssService {

    /**
     * 获取policy签名信息
     *
     * @param dir            存储在bucket的目录
     * @param expiredSeconds 过期时间
     * @return
     */
    PostObjectPolicy getPostObjectPolicy(String dir, long expiredSeconds);

    /**
     * 获取授权url
     *
     * @param objectName
     * @return
     */
    URL getSignedUrl(String objectName);

    /**
     * 获取sts
     *
     * @return
     */
    Map<String, Object> generateStsToken();
}
