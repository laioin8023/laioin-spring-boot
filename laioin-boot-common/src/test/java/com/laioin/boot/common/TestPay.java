package com.laioin.boot.common;

import com.alipay.api.AlipayClient;
import com.laioin.boot.common.alipay.AliPay;
import com.laioin.boot.common.alipay.AliPayConfig;
import com.laioin.boot.common.wxpay.IWXPayConfig;
import com.laioin.boot.common.wxpay.WXPay;
import com.laioin.boot.common.wxpay.WXPayConfigImpl;
import com.laioin.boot.common.wxpay.WXPayUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.laioin.boot.common.alipay.AliPay.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestPay {

    @Autowired
    private AliPayConfig aliPayConfig;
    @Autowired
    private WXPayConfigImpl wxPayConfig;

    /**
     * 支付宝，生成支付信息
     */
    @Test
    public void testAliPay() {
        AlipayClient client = AliPay.getAlipayClient(aliPayConfig);
        Map<String, Object> val = payInfo(client, "12112", "test", "test", new BigDecimal("0.01"), "http://www.jkfkadj.com");
        System.out.println(val);
    }

    /**
     * 微信生成支付信息
     *
     * @throws Exception
     */
    @Test
    public void testWxPay() throws Exception {
        WXPay wxPay = new WXPay(wxPayConfig);
        HashMap<String, String> datas = new HashMap<String, String>();
        datas.put("body", "test");//商品描述
        datas.put("out_trade_no", "OD12322");//商户订单号
        //data.put("device_info", "");//终端设备号(门店号或收银设备ID)，默认请传"WEB"
        datas.put("fee_type", "CNY");//货币类型
        datas.put("total_fee", new BigDecimal("10.0").multiply(new BigDecimal(100)).intValue() + "");//总金额 单位分
        datas.put("spbill_create_ip", "127.0.0.1");//终端IP
        datas.put("notify_url", "http://www.jkfkadj.com");
        datas.put("trade_type", "APP");
        Map<String, String> r = wxPay.unifiedOrder(datas);
        String return_code = r.get("return_code").toString();
        if (return_code.equalsIgnoreCase("FAIL")) {
            System.out.println("微信下单失败");
        }
        Map<String, String> map = WXPayUtil.returnWxPayParameter(r);
        String wxSign = wxPay.sign(map);
        map.put("sign", wxSign);
        map.put("orderId", "OD12322");
        System.out.println(map);
    }

}
