package com.czp.springlcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author      : CZP
 * @date        : Created in 2020-4-2 10:41:12
 * @description : 
 * @version     : 
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class OrderNacosFeignApplication85 {
	public static void main(String[] args) {
		SpringApplication.run(OrderNacosFeignApplication85.class, args);
	}
}
