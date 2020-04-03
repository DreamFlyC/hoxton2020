package com.czp.springcloud.controller;

import com.czp.springcloud.entities.Payment;
import com.czp.springcloud.entities.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-31 17:52:45
 * @description :
 */
@RestController
public class PaymentController {
	@Value("${server.port}")
	private String port;

	public static Map<Long, Payment> data = new HashMap<>();

	static {
		data.put(1L, new Payment(1L, UUID.randomUUID().toString().replace("-", "")));
		data.put(2L, new Payment(2L, UUID.randomUUID().toString().replace("-", "")));
		data.put(3L, new Payment(3L, UUID.randomUUID().toString().replace("-", "")));
	}

	@GetMapping(value = "/paymentSQL/{id}")
	public R paymentSQL(@PathVariable("id") Long id) {
		Payment payment = data.get(id);
		return R.ok().msg("from memory,server port:" + port).data(payment);
	}
}
