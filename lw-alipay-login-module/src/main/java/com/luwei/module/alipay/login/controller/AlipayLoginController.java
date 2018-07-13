package com.luwei.module.alipay.login.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("api/alipay")
public abstract class AlipayLoginController {

    private final Logger logger = LoggerFactory.getLogger(AlipayLoginController.class);

    @Value("${alipay.gateway}")
    private String gateway;

    @Value("${alipay.gateway}")
    private String appId;

    @Value("${alipay.public.key}")
    private String publicKey;

    @Value("${alipay.private.key}")
    private String privateKey;


    @GetMapping("callback")
    public void alipayLoginCallback(@RequestParam("auth_code") String authCode) {
        //使用auth_code换取接口access_token及用户userId
        AlipayClient alipayClient = new DefaultAlipayClient(gateway, appId, privateKey,
                "json", "UTF-8", publicKey, "RSA2");
        AlipaySystemOauthTokenRequest req = new AlipaySystemOauthTokenRequest();
        req.setCode(authCode);
        req.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse resp= alipayClient.execute(req);
            logger.info(" AliPay login callback response is: {}", resp);
            if (resp.isSuccess()) {
                String accessToken = resp.getAccessToken();
                AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
                AlipayUserInfoShareResponse response = alipayClient.execute(request, accessToken);

            }
        } catch (Exception e) {
            logger.info(" AliPay login callback error ", e);
        }
    }

}
