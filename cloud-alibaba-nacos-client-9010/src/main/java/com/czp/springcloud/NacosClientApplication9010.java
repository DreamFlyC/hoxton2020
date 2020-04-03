package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-20 16:30:02
 * @description : 
 * @version     : 
 */

@EnableDiscoveryClient
@SpringBootApplication
public class NacosClientApplication9010 {
	public static void main(String[] args) {
		SpringApplication.run(NacosClientApplication9010.class, args);
	}
}
