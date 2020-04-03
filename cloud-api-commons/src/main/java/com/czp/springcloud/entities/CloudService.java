package com.czp.springcloud.entities;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-10 10:47:06
 * @description :
 */
public interface CloudService {

	String PAYMENT_SERVICE = "cloud-payment-service";
	String EUREKA_SERVER_7002 = "cloud-eureka-server-7002";
	String EUREKA_SERVER_7001 = "cloud-eureka-server-7001";
	String ORDER_CONSUL_SERVICE = "cloud-consumer-consul-order";
	String ORDER_FEIGN_SERVICE = "cloud-consumer-openfeign-order";
	String PAYMENT_HYSTRIX_SERVICE="cloud-provider-hystrix-payment";
	String PAYMENT_NACOS_SERVICE="cloud-alibaba-nacos-ribbon-client";

}
