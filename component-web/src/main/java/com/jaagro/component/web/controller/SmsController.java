package com.jaagro.component.web.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jaagro.component.api.service.SmsService;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 短信通用方法
 *
 * @author gavin
 */
@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;

    /**
     * @param phoneNumber  接收短信的手机号码
     * @param templateCode 短信模版
     * @param templateMap  模版参数
     * @return
     * @Author gavin
     */
    @Ignore
    @PostMapping("/sendSMS")
    public BaseResponse sendSMS(@RequestParam String phoneNumber, @RequestParam String templateCode, @RequestParam Map<String, Object> templateMap) {

        try {
            SendSmsResponse response = smsService.sendSms(phoneNumber, templateCode, templateMap);

            return BaseResponse.successInstance(response);
        } catch (ClientException e) {
            e.printStackTrace();
            BaseResponse.errorInstance(ResponseStatusCode.SERVER_ERROR.getCode(), "消息发送失败");
        }
        return null;
    }
}
