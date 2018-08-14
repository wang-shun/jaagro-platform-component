package com.jaagro.component.web.controller;

import com.jaagro.component.api.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.ServiceResult;
import utils.VerificationPhone;

import java.util.Map;

/**
 * @author tony
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/sendMessage")
    public Map<String, Object> sendMessage(@RequestParam String phoneNumber) {
        if(VerificationPhone.isMobile(phoneNumber)){
            return verificationCodeService.sendMessage(phoneNumber);
        }
        return ServiceResult.error("手机号码不正确，请重新输入");
    }

    @PostMapping("/existMessage")
    public boolean existMessage(@RequestParam String phoneNumber,
                                @RequestParam String verificationCode){
        if(VerificationPhone.isMobile(phoneNumber)){
            return verificationCodeService.existMessage(phoneNumber, verificationCode);
        }
        return false;
    }
}
