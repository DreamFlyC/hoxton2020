package com.czp.ribbonlb;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-9 18:54:07
 * @description : 
 * @version     : 
 */
@Configuration
public class MySelfRule {

	@Bean
	public IRule myRule(){
		return new CustomLoadBalancer();
	}
}
