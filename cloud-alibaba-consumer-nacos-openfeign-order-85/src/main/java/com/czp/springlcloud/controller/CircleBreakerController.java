package com.czp.springlcloud.controller;

import com.czp.springcloud.entities.R;
import com.czp.springlcloud.feign.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author      : CZP
 * @date        : Created in 2020-4-2 10:49:11
 * @description : 
 * @version     : 
 */
 @RestController
public class CircleBreakerController {

 	@Autowired
    PaymentService paymentService;

	@GetMapping(value = "/consumer/paymentSQL/{id}")
	R paymentSQL(@PathVariable("id") Long id){
		return paymentService.paymentSQL(id);
	}

 }
