package com.czp.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-18 16:50:35
 * @description :
 */

@RefreshScope
@RestController
public class ConfigClientController {

	@Value("${server.port}")
	private String port;

	@Value("${config.info:''}")
	private String info;

	@GetMapping(value = "/info")
	public String info() {

		return "port：" + port + "\t info:" + info;
	}

}
