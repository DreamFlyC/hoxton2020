package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-20 16:30:02
 * @description :
 */

@EnableDiscoveryClient
@SpringBootApplication
public class NacosClientApplication9020 {

	public static void main(String[] args) {
		SpringApplication.run(NacosClientApplication9020.class, args);
	}
}
