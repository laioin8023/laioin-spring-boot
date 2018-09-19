package com.laioin.boot.common.alipay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

/**
 * 支付宝，配置信息
 */

@ContextConfiguration
@PropertySource("classpath:alipay_config.properties")
@Component
public class AliPayConfig {
    /**
     * 支付宝网关
     */
    @Value("${ali.pay.gate}")
    private String gate = "https://openapi.alipay.com/gateway.do";

    /**
     * 支付宝支付业务：入参app_id
     */
    @Value("${ali.pay.appId}")
    private String appId = "";

    /**
     * 支付宝唯一用户号
     */
    @Value("${ali.pay.pid}")
    private String pid = "";
    /**
     * 支付宝私钥
     */
    @Value("${ali.pay.private.key}")
    private String alipayPrivateKey = "";
    /**
     * 支付宝公钥
     */
    @Value("${ali.pay.public.key}")
    private String alipayPublicKey = "";

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAlipayPrivateKey() {
        return alipayPrivateKey;
    }

    public void setAlipayPrivateKey(String alipayPrivateKey) {
        this.alipayPrivateKey = alipayPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }
}
