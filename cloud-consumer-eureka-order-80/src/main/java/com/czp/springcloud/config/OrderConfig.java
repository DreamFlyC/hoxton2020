package com.czp.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-6 11:07:59
 * @description :
 */
@Configuration
public class OrderConfig {

	@Bean
//	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/*@Bean
	public IRule myRule() {
//		return new RoundRobinRule();
//		return new RandomRule();//达到的目的，用我们重新选择的随机算法替代默认的轮询。
		return new RetryRule();
	}*/
}
