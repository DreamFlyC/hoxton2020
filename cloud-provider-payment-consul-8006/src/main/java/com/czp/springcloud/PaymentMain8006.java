package com.czp.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-6 10:10:14
 * @description :
 */

@MapperScan(basePackages = "com.czp.springcloud.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8006 {
	public static void main(String[] args) {
		SpringApplication.run(PaymentMain8006.class, args);
	}

}
