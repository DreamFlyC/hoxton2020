package com.czp.springcloud.dao;

import com.czp.springcloud.entities.Payment;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-6 10:18:38
 * @description :
 */
public interface PaymentMapper {

	int create(Payment payment);

	Payment getPaymentById(Long id);
}
