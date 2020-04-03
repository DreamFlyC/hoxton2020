package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-18 16:44:38
 * @description : 消息总线刷新版 1.添加spring-boot-starter-actuator依赖，spring-cloud-starter-bus-amqp依赖
 *                             2.添加@RefreshScope
 *                             3.广播通知所有config客户端，发送POST请求:curl -X POST http://localhost:9700/actuator/bus-refresh
 *                             4.刷新指定客户端：curl -X POST http://localhost:9700/actuator/bus-refresh/config-client-9720:9720  （服务名:端口号）
 *
 * @version     : 
 */

@SpringBootApplication
public class ConfigClientApplication9720 {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication9720.class, args);
	}
}
