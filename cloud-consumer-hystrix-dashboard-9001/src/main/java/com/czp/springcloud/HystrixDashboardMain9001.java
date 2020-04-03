package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-12 15:50:34
 * @description : 
 * @version     : 
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001 {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardMain9001.class, args);
	}

}
