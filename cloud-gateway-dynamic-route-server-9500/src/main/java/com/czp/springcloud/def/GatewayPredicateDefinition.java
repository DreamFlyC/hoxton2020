package com.czp.springcloud.def;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-17 15:13:30
 * @description : 
 * @version     : 
 */
@Data
public class GatewayPredicateDefinition {

	//断言对应的Name
	private String name;
	//配置的断言规则
	private Map<String, String> args = new LinkedHashMap<>();
}
