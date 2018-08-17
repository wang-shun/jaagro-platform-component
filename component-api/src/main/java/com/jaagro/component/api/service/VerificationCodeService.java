package com.jaagro.component.api.service;

import java.util.Map;

/**
 * @author tony
 */
public interface VerificationCodeService {
    /**
     * 创建短信验证码
     * @param phoneNumber 手机号码
     * @return map
     */
    Map<String, Object> sendMessage(String phoneNumber);

    /**
     * 判断短信是否存在
     * @param phoneNumber 手机号码
     * @param verificationCode 验证码
     * @return 验证码是否有效
     */
    boolean existMessage(String phoneNumber, String verificationCode);

}
