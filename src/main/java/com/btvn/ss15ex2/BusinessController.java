package com.btvn.ss15ex2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {

    @GetMapping("/api/wallet/balance")
    public String getBalance() {
        return "Số dư tài khoản của bạn là: 5,000,000 VND";
    }
}
