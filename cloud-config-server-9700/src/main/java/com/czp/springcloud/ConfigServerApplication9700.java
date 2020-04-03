package com.czp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-18 15:54:19
 * @description : 
 * @version     : 
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication9700 {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication9700.class, args);
	}

}
