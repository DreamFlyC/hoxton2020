package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-6 17:30:34
 * @description : 
 * @version     : 
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication7001 {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication7001.class, args);
	}
}
