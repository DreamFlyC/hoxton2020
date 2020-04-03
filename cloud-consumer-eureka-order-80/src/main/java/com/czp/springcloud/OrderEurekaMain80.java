package com.czp.springcloud;

import com.czp.ribbonlb.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-6 10:58:09
 * @description : 
 * @version     : 
 */
@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "cloud-payment-service",configuration = MySelfRule.class)
public class OrderEurekaMain80 {
	public static void main(String[] args) {
		SpringApplication.run(OrderEurekaMain80.class, args);
	}
}
