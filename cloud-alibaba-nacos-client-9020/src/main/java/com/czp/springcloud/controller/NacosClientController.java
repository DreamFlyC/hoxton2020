package com.czp.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-20 16:31:55
 * @description :
 */

@RefreshScope
@RestController
public class NacosClientController {

	@Value("${server.port:''}")
	public String port;

	@Value("${config.info:''}")
	public String info;


	@GetMapping("/info")
	public String info() {
		return "port is:" + port + ",info:" + info;
	}

}
