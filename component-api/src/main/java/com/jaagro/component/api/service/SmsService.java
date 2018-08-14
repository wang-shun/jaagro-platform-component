package com.jaagro.component.api.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import java.util.Map;

/**
 * @author tony
 */
public interface SmsService {

    /**
     * 阿里云短信发送接口
     * @param phoneNumber
     * @param templateCode
     * @param templateMap
     * @return
     * @throws ClientException
     */
    SendSmsResponse sendSms(String phoneNumber, String templateCode, Map<String, Object> templateMap) throws ClientException;
}
