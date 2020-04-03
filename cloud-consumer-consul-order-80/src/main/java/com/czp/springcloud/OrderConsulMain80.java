package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-6 10:58:09
 * @description : 
 * @version     : 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain80 {
	public static void main(String[] args) {
		SpringApplication.run(OrderConsulMain80.class, args);
	}
}
