package com.czp.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-10 15:47:40
 * @description : 
 * @version     : 
 */
@Configuration
public class FeignOrderConfiguration {

	/**
	 * NONE, 不记录日志 (默认)。
	 * BASIC, 只记录请求方法和URL以及响应状态代码和执行时间。
	 * HEADERS, 记录请求和应答的头的基本信息。
	 * FULL, 记录请求和响应的头信息，正文和元数据。
	 *
	 */
	@Bean
	public Logger.Level logger(){
		return Logger.Level.FULL;
	}

}
