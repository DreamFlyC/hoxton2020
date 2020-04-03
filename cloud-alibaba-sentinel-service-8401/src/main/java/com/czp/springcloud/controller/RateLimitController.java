package com.czp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.czp.springcloud.entities.Payment;
import com.czp.springcloud.entities.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-31 15:39:04
 * @description :
 */

@RestController
public class RateLimitController {

		@GetMapping("/byResource")
	@SentinelResource(value = "byResource", blockHandler = "blockExceptionHandler")
	public R resource() {
		return R.ok().msg("按资源名称限流测试OK").data(Arrays.asList(new Payment(2020L, "serial001"),new Payment(2020L, "serial002")));
	}

	@GetMapping("/rateLimit/byUrl")
	@SentinelResource(value = "byUrl")
	public R byUrl() {
		return R.ok().msg("按Url名称限流测试OK").data(new Payment(2020L, "serial002"));
	}

	public R blockExceptionHandler(BlockException e) {
		return R.fail("rule:"+e.getRule()+"\n RuleLimitApp:"+e.getRuleLimitApp()+"\n "+e.getMessage()).msg("服务不可用");
	}

	/**
	 * [
	 *     {
	 *     "resource":"/rateLimit/byUrl",
	 *     "limitApp":"default",
	 *     "grade":1,
	 *     "count":1,
	 *     "strategy":0,
	 *     "controlBehavior":0,
	 *     "clusterMode":false
	 *     }
	 * ]
	 * resource： 资源名称
	 * limitApp： 来源应用
	 * grade： 阈值类型，0表示线程数，1表示 QPS
	 * count: 单机阈值
	 * strategy:  流控模式， 0 - 表示直接，1表示关联，2表示链路
	 * controlBehavior： 流控效果，0表示快速失败，1表示Warm up , 2表示排队
	 * clusterMode： 是否集群。
	 *
	 *
	 */

}
