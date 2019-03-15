package com.song.pay.api.service;

import com.song.pay.api.entity.PaymentInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 提供支付信息接口服务
 * created on 2019/3/15 14:20
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@RequestMapping("/pay")
public interface PaymentInfoService {

    /**
     * 换取支付令牌
     *
     * @return
     */
    @RequestMapping("/addPayInfoToken")
    public Map<String, Object> addPayInfoToken(@RequestBody PaymentInfo paymentInfo);

    /**
     * 使用token查找支付信息
     *
     * @param token
     * @return
     */
    @RequestMapping("/getPayInfoToken")
    public Map<String, Object> getPayInfoToken(@RequestParam("token") String token);

    /**
     * 使用订单号查找支付信息
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/getByOrderIdPayInfo")
    public Map<String, Object> getByOrderIdPayInfo(@RequestParam("orderId") String orderId);

    /**
     * 更新支付信息
     *
     * @param paymentInfo
     * @return
     */
    @RequestMapping("/updatePayInfo")
    public Map<String, Object> updatePayInfo(@RequestBody PaymentInfo paymentInfo);

}
