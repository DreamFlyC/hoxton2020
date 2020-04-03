package com.czp.springcloud.def;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-17 15:13:03
 * @description : 路由模型
 * @version     : 
 */
@Data
public class GatewayRouteDefinition {
	//路由的Id
	private String id;
	//路由断言集合配置
	private List<GatewayPredicateDefinition> predicates = new ArrayList<>();
	//路由过滤器集合配置
	private List<GatewayFilterDefinition> filters = new ArrayList<>();
	//路由规则转发的目标uri
	private String uri;
	//路由执行的顺序
	private int order = 0;
	//元数据
	private Map<String,Object> metadatas;
}
