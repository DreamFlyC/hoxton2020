package com.czp.springcloud.vo;

import lombok.Data;
import org.springframework.cloud.client.ServiceInstance;

import java.io.Serializable;
import java.util.List;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-7 11:58:20
 * @description :
 */
@Data
public class DiscoveryResponse implements Serializable {

	List<String> services;
	List<ServiceInstance> instances;
	String description;
	int order;
}
