package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-7 15:38:03
 * @description : 
 * @version     : 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ZkOrderApplication80 {
	public static void main(String[] args) {
		SpringApplication.run(ZkOrderApplication80.class, args);
	}
}
