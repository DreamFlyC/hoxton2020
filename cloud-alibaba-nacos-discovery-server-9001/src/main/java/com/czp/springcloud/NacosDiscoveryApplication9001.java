package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-20 15:42:35
 * @description :
 * @version     :
 */

@EnableDiscoveryClient
@SpringBootApplication
public class NacosDiscoveryApplication9001 {

	public static void main(String[] args) {
		SpringApplication.run(NacosDiscoveryApplication9001.class, args);
	}
}
