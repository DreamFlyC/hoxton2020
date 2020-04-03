package com.czp.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-6 10:10:14
 * @description :
 */

@MapperScan(basePackages = "com.czp.springcloud.dao")
@SpringBootApplication
@EnableDiscoveryClient  // 该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class PaymentMain8004 {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		
		SpringApplication.run(PaymentMain8004.class, args);
	}

}
