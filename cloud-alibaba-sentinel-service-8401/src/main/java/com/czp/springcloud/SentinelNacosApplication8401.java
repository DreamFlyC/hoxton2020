package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-27 10:51:33
 * @description : 
 * @version     : 
 */
 @SpringBootApplication
 @EnableDiscoveryClient
public class SentinelNacosApplication8401 {

	public static void main(String[] args) {
		SpringApplication.run(SentinelNacosApplication8401.class, args);
	}
}
