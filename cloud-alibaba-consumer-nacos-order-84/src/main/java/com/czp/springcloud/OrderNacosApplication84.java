package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-31 18:05:59
 * @description : 
 * @version     : 
 */
 
@SpringBootApplication
@EnableDiscoveryClient
public class OrderNacosApplication84 {
	public static void main(String[] args) {
		SpringApplication.run(OrderNacosApplication84.class, args);
	}

}
