package com.song.pay.service.Impl;

import com.song.common.api.BaseApiService;
import com.song.common.redis.BaseRedisService;
import com.song.common.utils.DateUtils;
import com.song.common.utils.TokenUtils;
import com.song.pay.api.entity.PaymentInfo;
import com.song.pay.api.service.PaymentInfoService;
import com.song.pay.dao.PaymentInfoDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * created on 2019/3/15 14:25
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@RestController
public class PaymentInfoServiceImpl extends BaseApiService implements PaymentInfoService {

    @Autowired
    private PaymentInfoDao paymentInfoDao;

    @Autowired
    private BaseRedisService baseRedisService;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public Map<String, Object> addPayInfoToken(@RequestBody PaymentInfo paymentInfo) {
        paymentInfo.setCreated(DateUtils.getTimestamp());
        paymentInfo.setUpdated(DateUtils.getTimestamp());
        paymentInfoDao.savePaymentType(paymentInfo);
        Long paymentInfoId = paymentInfo.getId();
        if (null == paymentInfoId) {
            return setResultError("系统错误，为获取到支付id！");
        }
        String token = tokenUtils.getToken();
        baseRedisService.setString(token, paymentInfoId + "");
        return setResultSuccessData(token);
    }

    @Override
    public Map<String, Object> getPayInfoToken(@RequestParam("token") String token) {
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空！");
        }
        //redis里存的 key 对应的是 id
        Long payInfoId = Long.valueOf(baseRedisService.get(token));
        PaymentInfo paymentInfo = paymentInfoDao.getPaymentInfo(payInfoId);
        return setResultSuccessData(paymentInfo);
    }

    @Override
    public Map<String, Object> getByOrderIdPayInfo(@RequestParam("orderId") String orderId) {
        PaymentInfo paymentInfo = paymentInfoDao.getByOrderIdPayInfo(orderId);
        if (null == paymentInfo){
            return setResultError("未查到支付相关信息！");
        }
        return setResultSuccessData(paymentInfo);
    }

    @Override
    public Map<String, Object> updatePayInfo(@RequestBody PaymentInfo paymentInfo) {
        paymentInfo.setUpdated(DateUtils.getTimestamp());
        paymentInfoDao.updatePayInfo(paymentInfo);
        return setResultSuccess();
    }


}
