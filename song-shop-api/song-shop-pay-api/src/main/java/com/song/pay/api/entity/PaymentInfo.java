package com.song.pay.api.entity;

import com.song.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInfo extends BaseEntity {

	/**
	 * 支付类型
	 */
	private Long typeId;
	/**
	 * 订单编号
	 */
	private String orderId;
	/**
	 * 第三方平台支付id
	 */
	private String platformorderId;
	/**
	 * 价格 以分为单位
	 */
	private Long price;
	/**
	 * 支付来源
	 */
	private String source;
	/**
	 * 支付来源
	 */
	private Integer state;
	/**
	 * 支付报文
	 */
	private String payMessage;

}
