package com.czp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.czp.springcloud.entities.CloudService;
import com.czp.springcloud.entities.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-4-1 16:09:58
 * @description :
 */
@RestController
@Slf4j
public class CircleBreakerController {

	@Autowired
	public RestTemplate restTemplate;

	@RequestMapping("/consumer/fallback/{id}")
	@SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
			defaultFallback = "defaultFallback",exceptionsToIgnore = IllegalArgumentException.class)
	public R fallback(@PathVariable("id") Long id) {
		R result = restTemplate.getForObject("http://" + CloudService.PAYMENT_NACOS_SERVICE + "/paymentSQL/" + id, R.class, id);
		if (id == 4) {
			throw new IllegalArgumentException("IllegalArgumentException---非法参数异常！！！，id:" + id);
		} else if (result != null && result.getData() == null) {
			throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常，id:" + id);
		}
		return result;
	}

	public R handlerFallback(Long id, Throwable e) {
		return R.fail("handlerFallback:" + e.getMessage());
	}

	public R blockHandler(Long id, BlockException e) {
		return R.fail("blockHandler:" + e.getMessage());
	}

	public R defaultFallback(Long id, Throwable e) {
		return R.fail("defaultFallback:" + e.getMessage());
	}
}
