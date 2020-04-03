package com.czp.springcloud.controller;

import com.czp.springcloud.entities.CommonResult;
import com.czp.springcloud.entities.Payment;
import com.czp.springcloud.entities.R;
import com.czp.springcloud.service.PaymentService;
import com.czp.springcloud.vo.DiscoveryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

	@Resource
	private PaymentService paymentService;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${server.port}")
	private String port;

	@PostMapping(value = "/create")
	public CommonResult create(@RequestBody Payment payment) {
		int result = paymentService.create(payment);
		log.info("插入结果:" + result);
		if (result > 0) {
			return new CommonResult().ok().data(result);
		} else {
			return new CommonResult().fail();
		}
	}

	@GetMapping(value = "/get/{id}")
	public CommonResult getPaymentById(@PathVariable("id") Long id) {
		Payment payment = paymentService.getPaymentById(id);
		log.info("获得结果:" + payment);
		return new CommonResult().ok().data(payment);
	}

	@GetMapping(value = "/port")
	public CommonResult getPort() {
		return new CommonResult().ok().data("当前端口为：" + port);
	}

	@GetMapping("/discovery/r")
	public R discoveryR() {
		List<String> services = discoveryClient.getServices();
		List<ServiceInstance> instances = new ArrayList<>();
		for (String service : services) {
			instances.addAll(discoveryClient.getInstances(service));
		}
		String description = discoveryClient.description();
		int order = discoveryClient.getOrder();
		DiscoveryResponse response = new DiscoveryResponse();
		response.setServices(services);
		response.setInstances(instances);
		response.setDescription(description);
		response.setOrder(order);
		return R.ok(response);
	}

	@GetMapping("/discovery/c")
	public CommonResult discoveryC() {
		CommonResult result = new CommonResult(true);
		List<String> services = discoveryClient.getServices();
		result.data(services);
		for (String service : services) {
			result.put("instance", discoveryClient.getInstances(service));
		}
		result.data(discoveryClient.description());
		result.data(discoveryClient.getOrder());
		return result;
	}

	@RequestMapping(value = "/lb")
	public String getPaymentLb(){
		return port;
	}

}
