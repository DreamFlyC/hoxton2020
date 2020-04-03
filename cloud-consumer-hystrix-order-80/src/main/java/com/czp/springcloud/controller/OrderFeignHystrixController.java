package com.czp.springcloud.controller;

import com.czp.springcloud.feign.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-11 15:17:40
 * @description :
 */
@Slf4j
@RestController
@RequestMapping(value = "/consumer/hystrix")
//@DefaultProperties(defaultFallback = "fallbackTimeoutMethod",commandProperties = {
//		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//})
public class OrderFeignHystrixController {

	@Autowired
	PaymentHystrixService paymentHystrixService;

	@GetMapping(value = "/ok/{id}")
	public String paymentOK(@PathVariable("id") Integer id) {
		return paymentHystrixService.paymentOK(id);
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
	})
	@GetMapping(value = "/timeout/{id}")
	public String timeout(@PathVariable("id") Integer id) {
		return paymentHystrixService.timeout(id);
	}

	public String fallbackTimeoutMethod(@PathVariable("id") Integer id) {
		return "我是消费者80，对方系统繁忙请稍后再试";
	}

}
