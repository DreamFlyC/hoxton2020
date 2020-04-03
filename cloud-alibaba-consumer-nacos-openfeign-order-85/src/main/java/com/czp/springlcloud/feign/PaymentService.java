package com.czp.springlcloud.feign;

import com.czp.springcloud.entities.CloudService;
import com.czp.springcloud.entities.R;
import com.czp.springlcloud.service.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-4-2 10:41:47
 * @description :
 */
@Primary
@FeignClient(value = CloudService.PAYMENT_NACOS_SERVICE, fallback = PaymentFallbackService.class)
public interface PaymentService {

	@GetMapping(value = "/paymentSQL/{id}")
	R paymentSQL(@PathVariable("id") Long id);

}
