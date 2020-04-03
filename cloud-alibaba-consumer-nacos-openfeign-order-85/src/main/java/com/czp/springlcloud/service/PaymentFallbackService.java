package com.czp.springlcloud.service;

import com.czp.springcloud.entities.Payment;
import com.czp.springcloud.entities.R;
import com.czp.springlcloud.feign.PaymentService;
import org.springframework.stereotype.Component;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-4-2 10:45:32
 * @description :
 */
@Component
public class PaymentFallbackService implements PaymentService {
	@Override
	public R paymentSQL(Long id) {
		return R.fail(new Payment(id, "errorSerial")).code(444).msg("服务降级返回");
	}
}
