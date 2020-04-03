package com.czp.springcloud.feign;

import com.czp.springcloud.entities.CloudService;
import com.czp.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-10 10:45:53
 * @description :
 */
@RequestMapping("/payment")
@FeignClient(value = CloudService.PAYMENT_SERVICE)
public interface FeignOrderService {

	@GetMapping(value = "/get/{id}")
	CommonResult getPaymentById(@PathVariable("id") Long id);

}
