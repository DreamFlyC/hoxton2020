package com.czp.springcloud.entity;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-17 15:13:34
 * @description : 创建过滤器模型
 * @version     : 
 */
@Data
public class GatewayFilterDefinition {
	//Filter Name
	private String name;
	//对应的路由规则
	private Map<String, String> args = new LinkedHashMap<>();
}
