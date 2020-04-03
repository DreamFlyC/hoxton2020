package com.czp.springcloud.controller;

import com.czp.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-6 10:17:58
 * @description :
 */
@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {

	@Value("${server.port}")
	private String port;

	@GetMapping(value = "/port")
	public CommonResult getPort() {
		return new CommonResult().ok().data("当前端口为：" + port);
	}

	@GetMapping(value = "/consul")
	public String consul() {
		return "springcloud with consul:" + port + "\t" + UUID.randomUUID().toString();
	}
}
