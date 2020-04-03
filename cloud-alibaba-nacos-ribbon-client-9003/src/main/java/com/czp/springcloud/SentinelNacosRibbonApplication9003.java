package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-31 17:49:21
 * @description : 
 * @version     : 
 */

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelNacosRibbonApplication9003 {
	public static void main(String[] args) {
		SpringApplication.run(SentinelNacosRibbonApplication9003.class, args);
	}
}
