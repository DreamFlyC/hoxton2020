package com.czp.springcloud.controller;

import com.czp.springcloud.entities.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

	@GetMapping("/port")
	public CommonResult port(){
		return restTemplate.getForObject(PAYMENT_URI+"/payment/port", CommonResult.class);
	}

	@GetMapping("/consul")
	public String consul(){
		return restTemplate.getForObject(PAYMENT_URI+"/payment/consul", String.class);
	}
}