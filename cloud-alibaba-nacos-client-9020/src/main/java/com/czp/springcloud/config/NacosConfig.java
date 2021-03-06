package com.czp.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-20 16:30:56
 * @description : 
 * @version     : 
 */

@Configuration
public class NacosConfig implements Serializable {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
