package com.czp.springcloud.service;

import com.czp.springcloud.entities.Payment;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-6 10:19:03
 * @description : 
 * @version     : 
 */
public interface PaymentService {
	int create(Payment payment);

	Payment getPaymentById(Long id);
}
