package com.jaagro.component.api.service;

import java.util.Map;

/**
 * @author tony
 */
public interface VerificationCodeService {
    /**
     * 创建短信验证码
     * @param phoneNumber 手机号码
     * @return
     */
    Map<String, Object> sendMessage(String phoneNumber);

    /**
     * 判断短信是否存在
     * @param verificationCode 验证码
     * @return
     */
    boolean existMessage(String phoneNumber, String verificationCode);

}
