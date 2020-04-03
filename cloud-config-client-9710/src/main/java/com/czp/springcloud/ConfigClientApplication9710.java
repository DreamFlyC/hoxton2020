package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-18 16:44:38
 * @description : 手动刷新版config 1.添加spring-boot-starter-actuator依赖，
 *                                2. 添加@RefreshScope,
 *                                3.发送POST请求:http://localhost:9710/actuator/refresh
 * @version     : 
 */

@SpringBootApplication
public class ConfigClientApplication9710 {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication9710.class, args);
	}
}
