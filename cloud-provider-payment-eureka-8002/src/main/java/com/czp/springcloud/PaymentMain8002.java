package com.czp.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-6 10:10:14
 * @description :
 */

@MapperScan(basePackages = "com.czp.springcloud.dao")
@SpringBootApplication
public class PaymentMain8002 {
	public static void main(String[] args) {
		SpringApplication.run(PaymentMain8002.class, args);
	}

}
