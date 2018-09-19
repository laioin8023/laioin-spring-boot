package com.laioin.boot.common.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * 支付宝支付类
 */
public class AliPay {

    /**
     * 编码方式
     */
    private static final String CHARSET = "utf-8";

    private static Logger logger = LoggerFactory.getLogger(AliPay.class);

    private static AlipayClient ALIPAY_CLIENT;

    /**
     * 创建客户端
     *
     * @return
     */
    public static AlipayClient getAlipayClient(AliPayConfig config) {
        if (ALIPAY_CLIENT == null) {
            synchronized (AliPay.class) {
                if (ALIPAY_CLIENT == null) {
                    ALIPAY_CLIENT = new DefaultAlipayClient(config.getGate(),
                            config.getAppId(),
                            config.getAlipayPrivateKey(),
                            "json",
                            CHARSET,
                            config.getAlipayPublicKey(),
                            "RSA2");
                }
            }
            return ALIPAY_CLIENT;
        } else {
            return ALIPAY_CLIENT;
        }
    }

    /**
     * 生成订单的支付信息
     *
     * @param alipayClient
     * @param orderNo      订单号
     * @param body         订单内容描述
     * @param subject      标题
     * @param price        价格
     * @param notifyUrl    回调地址
     * @return
     */
    public static Map<String, Object> payInfo(AlipayClient alipayClient, String orderNo, String body, String subject, BigDecimal price, String notifyUrl) {
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(orderNo);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(price.toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            logger.info("请求 Alipay 接口返回支付信息：[{}].", response.getBody());
            Map<String, Object> map = new HashMap<>();
            map.put("orderNo", orderNo);
            map.put("payInfo", response.getBody());
            //就是payInfo可以直接给客户端请求，无需再做处理。
            return map;
        } catch (AlipayApiException e) {
            logger.error("请求 Alipay 接口返回支付信息出错", e);
        }
        return null;
    }
}
