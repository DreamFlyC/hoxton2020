package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-17 15:08:11
 * @description : 
 * @version     : 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayDynamicRouteApplication9510 {
	public static void main(String[] args) {
		SpringApplication.run(GatewayDynamicRouteApplication9510.class, args);
	}
}
