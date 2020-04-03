package com.czp.springcloud.controller;

import com.czp.springcloud.entities.CommonResult;
import com.czp.springcloud.feign.FeignOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	FeignOrderService feignOrderService;

	@GetMapping("/payment/{id}")
	public CommonResult getPaymentById(@PathVariable("id")Long id){
		return feignOrderService.getPaymentById(id);
	}

}
