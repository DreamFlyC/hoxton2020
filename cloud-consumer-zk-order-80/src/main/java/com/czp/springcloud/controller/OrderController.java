package com.czp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-7 15:40:56
 * @description :
 */
@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {

	@Autowired
	RestTemplate restTemplate;

	private static final String PAYMENT_URI = "http://cloud-payment-service";

	@GetMapping(value = "/zk")
	public String zk(){
		return restTemplate.getForObject(PAYMENT_URI+"/payment/zk", String.class);
	}
}
