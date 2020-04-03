package com.czp.springcloud.feign;

import org.springframework.stereotype.Component;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-11 17:24:25
 * @description : 
 * @version     : 
 */
@Component
public class PaymentHystrixFallbackService implements PaymentHystrixService {
	@Override
	public String paymentOK(Integer id) {
		return "--------PaymentHystrixFallbackService#paymentOK,哈哈哈";
	}

	@Override
	public String timeout(Integer id) {
		return "--------PaymentHystrixFallbackService#timeout,超时啦";
	}
}
