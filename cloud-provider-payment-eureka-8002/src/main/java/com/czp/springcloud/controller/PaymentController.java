package com.czp.springcloud.controller;

import com.czp.springcloud.entities.CommonResult;
import com.czp.springcloud.entities.Payment;
import com.czp.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-6 10:17:58
 * @description :
 */
@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {

	@Resource
	private PaymentService paymentService;

	@Value("${server.port}")
	private String port;

	@PostMapping(value = "/create")
	public CommonResult create(@RequestBody Payment payment) {
		int result = paymentService.create(payment);
		log.info("插入结果:" + result);
		if (result > 0) {
			return new CommonResult().ok().data(result);
		} else {
			return new CommonResult().fail();
		}
	}

	@GetMapping(value = "/get/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);
		log.info("获得结果:" + payment);
		return new CommonResult().ok().data(payment);
	}

	@GetMapping(value = "/port")
	public CommonResult getPort() {
		return new CommonResult().ok().data("当前端口为：" + port);
	}

	@GetMapping(value = "/lb")
	public String getPaymentLB(){
		return port;
	}

}
