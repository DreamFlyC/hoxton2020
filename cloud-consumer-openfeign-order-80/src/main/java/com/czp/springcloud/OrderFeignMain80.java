package com.czp.springcloud;

import com.czp.springcloud.config.FeignOrderConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-6 10:58:09
 * @description :
 * @version     : 

 * Feign原理简述
 * 启动时，程序会进行包扫描，扫描所有包下所有@FeignClient注解的类，并将这些类注入到spring的IOC容器中。当定义的Feign中的接口被调用时，
 * 通过JDK的动态代理来生成RequestTemplate。
 * RequestTemplate中包含请求的所有信息，如请求参数，请求URL等。
 * RequestTemplate声场Request，然后将Request交给client处理，这个client默认是JDK的HTTPUrlConnection，也可以是OKhttp、Apache的HTTPClient等。
 * 最后client封装成LoadBaLanceClient，结合ribbon负载均衡地发起调用。
 *
 */
@EnableFeignClients(defaultConfiguration = FeignOrderConfiguration.class)
@SpringBootApplication
@EnableDiscoveryClient
public class OrderFeignMain80 {
	public static void main(String[] args) {
		SpringApplication.run(OrderFeignMain80.class, args);
	}
}
