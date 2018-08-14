package com.jaagro.component.biz.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.google.common.collect.Maps;
import com.jaagro.component.api.service.SmsService;
import com.jaagro.component.api.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import utils.ServiceResult;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author tony
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    /**
     * 短信校验码模板code
     */
    private String smsTemplateCode = "SMS_141605531";

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SmsService smsService;

    /**
     * 创建短信验证码
     *
     * @param phoneNumber 手机号码
     * @return
     */
    @Override
    public Map<String, Object> sendMessage(String phoneNumber) {
        Random random = new Random();
        //获取5位随机验证码
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder verificationCodeBuilder = null;
        for(int i = 0; i < 5; i++){
            verificationCodeBuilder = stringBuilder.append(random.nextInt(9));
        }
        String verificationCode = verificationCodeBuilder.toString();
        Map<String, Object> map = Maps.newLinkedHashMap();
        map.put("code", verificationCode);
        try {
            smsService.sendSms(phoneNumber, smsTemplateCode, map);
        } catch (ClientException e) {
            e.printStackTrace();
            return ServiceResult.error("短信发送失败");
        }
        redisTemplate.opsForValue().set(phoneNumber, verificationCode, 10, TimeUnit.MINUTES);
        return ServiceResult.toResult("验证码发送成功");
    }

    /**
     * 判断短信是否存在
     *
     * @param phoneNumber 手机号码
     * @return
     */
    @Override
    public boolean existMessage(String phoneNumber, String verificationCode) {
        String verificationCodeRedis = redisTemplate.opsForValue().get(phoneNumber);
        if(verificationCode.equals(verificationCodeRedis)){
            return true;
        }
        return false;
    }
}
