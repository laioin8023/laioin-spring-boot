package com.laioin.boot.common.wxpay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 微信支付配置实现类
 */
@Configuration
@PropertySource("classpath:wxpay_config.properties")
@Component
public class WXPayConfigImpl implements IWXPayConfig {

    private byte[] certData;

    /**
     * 证书文件路径
     */
    @Value("${wx.pay.cert.path}")
    private String certPath;
    /**
     * 微信 appid
     */
    @Value("${wx.pay.app.id}")
    private String appId;
    /**
     * 微信商户id
     */
    @Value("${wx.pay.app.mch.id}")
    private String mchId;
    /**
     * app key
     */
    @Value("${wx.pay.key}")
    private String key;


    public WXPayConfigImpl() throws Exception {
        File file = new File(certPath);
        if (file.exists()) {
            InputStream certStream = new FileInputStream(file);
            this.certData = new byte[(int) file.length()];
            certStream.read(this.certData);
            certStream.close();
        }
    }


    @Override
    public String getAppID() {
        return this.appId;
    }

    @Override
    public String getMchID() {
        return this.mchId;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    @Override
    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return WXPayConstants.DOMAIN_API;
    }

    public String getAlternateDomain() {
        return WXPayConstants.DOMAIN_API2;
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }


    @Override
    public boolean shouldAutoReport() {
        return true;
    }

    @Override
    public int getReportQueueMaxSize() {
        return 10000;
    }
}
