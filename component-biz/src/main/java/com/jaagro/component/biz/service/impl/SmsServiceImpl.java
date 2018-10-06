package com.jaagro.component.biz.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaagro.component.api.service.SmsService;
import com.jaagro.utils.VerificationPhone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author tony
 */
@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);
    /**
     * 短信账号信息
     */
    private static String ACCESS_KEY_ID;
    private static String ACCESS_KEY_SECRET;
    private static String SIGN_NAME;

    @Value("${aliyun.sms.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        ACCESS_KEY_ID = accessKeyId;
    }

    @Value("${aliyun.sms.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        ACCESS_KEY_SECRET = accessKeySecret;
    }

    @Value("${aliyun.sms.signName}")
    public void setSignName(String signName) {
        SIGN_NAME = signName;
    }

    /**
     * 产品域名,开发者无需替换
     */
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String PRODUCT = "Dysmsapi";

    private static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 阿里云短信发送接口
     *
     * @param phoneNumber  手机号码
     * @param templateCode 模板编号，在阿里云短信控制台
     * @param templateMap  短信模板内的变量用一个map传过来
     * @return
     * @throws ClientException
     */
    @Override
    public SendSmsResponse sendSms(String phoneNumber, String templateCode, Map<String, Object> templateMap) throws ClientException {
        if (!VerificationPhone.isMobile(phoneNumber)) {
            throw new RuntimeException("请输入有效的11位中国大陆手机号！");
        }
        //将短信模板变量map转换成json
        String templateParams = null;
        try {
            templateParams = MAPPER.writeValueAsString(templateMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("模板变量转换失败：" + e.getMessage());

        }
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNumber);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SIGN_NAME);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParams);
        //此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
}
