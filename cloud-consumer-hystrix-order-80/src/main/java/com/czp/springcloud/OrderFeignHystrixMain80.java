package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-11 15:11:00
 * @description : 
 * @version     : 
 */
@EnableFeignClients
@SpringCloudApplication
public class OrderFeignHystrixMain80 {
	public static void main(String[] args) {
		SpringApplication.run(OrderFeignHystrixMain80.class, args);
	}
}
