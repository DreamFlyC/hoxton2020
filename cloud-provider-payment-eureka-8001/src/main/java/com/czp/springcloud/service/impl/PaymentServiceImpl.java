package com.czp.springcloud.service.impl;

import com.czp.springcloud.dao.PaymentMapper;
import com.czp.springcloud.entities.Payment;
import com.czp.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-6 10:19:22
 * @description : 
 * @version     : 
 */
@Service
public class PaymentServiceImpl implements PaymentService {

	@Resource
	PaymentMapper paymentMapper;


	@Override
	public int create(Payment payment) {
		return paymentMapper.create(payment);
	}

	@Override
	public Payment getPaymentById(Long id) {
		return paymentMapper.getPaymentById(id);
	}
}
