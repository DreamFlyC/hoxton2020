package com.czp.springcloud;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-18 10:05:47
 * @description : 
 * @version     : 
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class AdminServerApplication8769 {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication8769.class, args);
	}
}
