package com.czp.springcloud.controller;

import com.czp.springcloud.entities.CommonResult;
import com.czp.springcloud.entities.Payment;
import com.czp.springcloud.entities.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-6 11:01:32
 * @description :
 */
@RestController
@RequestMapping("/consumer")
public class OrderController {

	@Autowired
	RestTemplate restTemplate;

	private static final String PAYMENT_URI = "http://cloud-payment-service";

	@PostMapping("/create")
	public CommonResult create(Payment payment) {
		return restTemplate.postForObject(PAYMENT_URI + "/payment/create", payment, CommonResult.class);
	}

	@GetMapping("/get/{id}")
	public CommonResult get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(PAYMENT_URI + "/payment/get/" + id, CommonResult.class);
	}

	@GetMapping("/port")
	public CommonResult port() {
		return restTemplate.getForObject(PAYMENT_URI + "/payment/port", CommonResult.class);
	}

	@GetMapping("/ribbon/{id}")
	public R ribbon(@PathVariable("id") Long id) {
		ResponseEntity<R> entity = restTemplate.getForEntity(PAYMENT_URI + "/payment/get/" + id, R.class);
		if (entity.getStatusCode().is2xxSuccessful()) {
			return entity.getBody();
		}
		return R.fail();
	}



}
