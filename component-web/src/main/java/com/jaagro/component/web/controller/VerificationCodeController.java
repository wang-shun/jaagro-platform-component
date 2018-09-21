package com.jaagro.component.web.controller;

import com.jaagro.component.api.service.VerificationCodeService;
import com.jaagro.utils.BaseResponse;
import com.jaagro.utils.ResponseStatusCode;
import com.jaagro.utils.ServiceResult;
import com.jaagro.utils.VerificationPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping("/sendMessage")
    public BaseResponse sendMessage(@RequestParam String phoneNumber) {
        if (VerificationPhone.isMobile(phoneNumber)) {
            return BaseResponse.service(verificationCodeService.sendMessage(phoneNumber));
        }
        return BaseResponse.service(ServiceResult.error(ResponseStatusCode.QUERY_DATA_ERROR.getCode(), "手机号码不正确，请重新输入"));
    }

    @PostMapping("/existMessage")
    public boolean existMessage(@RequestParam String phoneNumber,
                                @RequestParam String verificationCode) {
        if (VerificationPhone.isMobile(phoneNumber)) {
            return verificationCodeService.existMessage(phoneNumber, verificationCode);
        }
        return false;
    }
}
