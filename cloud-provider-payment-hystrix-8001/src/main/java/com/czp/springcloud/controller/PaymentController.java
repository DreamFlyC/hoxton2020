package com.czp.springcloud.controller;

import com.czp.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-11 14:31:28
 * @description :
 */
@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@Value("${server.port}")
	private String port;

	@GetMapping("/hystrix/ok/{id}")
	public String ok(@PathVariable("id") Integer id) {
		String result = paymentService.paymentOK(id);
		log.info("*********result:" + result);
		return result;
	}

	@GetMapping("/hystrix/timeout/{id}")
	public String timeout(@PathVariable("id") Integer id) {
		String result = paymentService.paymentTimeout(id);
		log.info("*********result:" + result);
		return result;
	}

	@HystrixCommand(fallbackMethod = "defaultFallback")
	@GetMapping("/hystrix/circuit/{num}")
	public String circuit(@PathVariable("num") Integer num) throws Exception {
		if (num >= 10) {
			throw new Exception("我不能大于10," + num);
		} else {
			return UUID.randomUUID().toString();
		}
	}

	public String defaultFallback(Integer num, Throwable t) {
		return "我报错了,你的值为：" + num + "\t 报错信息：" + t;
	}
}
