package com.czp.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.czp.springcloud.entities.Result;
import com.czp.springcloud.entity.CustomerRouteDefinition;
import com.czp.springcloud.entity.GatewayFilterDefinition;
import com.czp.springcloud.entity.GatewayPredicateDefinition;
import com.czp.springcloud.entity.GatewayRouteDefinition;
import com.czp.springcloud.service.DynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-17 15:27:49
 * @description :  动态路由实现
 */

@Slf4j
@RestController
@RequestMapping("/route")
public class DynamicRouteController {

	private final DynamicRouteService dynamicRouteService;

	public DynamicRouteController(DynamicRouteService dynamicRouteService) {
		this.dynamicRouteService = dynamicRouteService;
	}

	@GetMapping("/get")
	public Flux<Map<String, Object>> get() {
		return dynamicRouteService.get();
	}

	//增加路由
	@PostMapping("/add")
	public Result add(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
		try {
			RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
			dynamicRouteService.add(definition);
		} catch (Exception e) {
			log.error("增加路由失败", e);
			return Result.fail().msg("增加路由失败");
		}
		return Result.ok();
	}

	//删除路由
	@DeleteMapping("/delete/{routeId}")
	public Result delete(@PathVariable("routeId") String routeId) {
		try {
			dynamicRouteService.delete(routeId);
			return Result.ok();
		} catch (Exception e) {
			log.error("删除路由失败", e);
			return Result.fail().msg("删除路由失败");
		}
	}

	//更新路由
	@PutMapping("/update")
	public Result update(@RequestBody GatewayRouteDefinition gatewayRouteDefinition) {
		RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
		String result = dynamicRouteService.update(definition);
		if ("success".equals(result)) {
			return Result.ok("routeDefinition", definition).msg("更新路由成功");
		}
		return Result.fail();
	}

	/**
	 * 把传递进来的参数转换成路由对象
	 */
	@SuppressWarnings("unchecked")
	private RouteDefinition assembleRouteDefinition(CustomerRouteDefinition c) {
		RouteDefinition definition = new RouteDefinition();
		definition.setId(c.getRouteId());
		definition.setOrder(c.getOrder());

		// 设置断言
		List<PredicateDefinition> predicates = JSON.parseArray(c.getPredicates(), PredicateDefinition.class);
		definition.setPredicates(predicates);

		// 设置过滤器
		List<FilterDefinition> filters = JSON.parseArray(c.getPredicates(), FilterDefinition.class);
		definition.setFilters(filters);

		//设置metadata
		Map<String, Object> metadatas = JSON.parseObject(c.getMetadatas(), Map.class);
		if (metadatas != null && metadatas.size() > 0) {
			definition.setMetadata(metadatas);
		}

		//设置URI
		URI uri = null;
		if (c.getUri() != null) {
			if (c.getUri().startsWith("http")) {
				UriComponentsBuilder.fromHttpUrl(c.getUri()).build().toUri();
			} else {
				// uri为 lb://consumer-service 时使用下面的方法
				uri = URI.create(c.getUri());
			}
		}
		definition.setUri(uri);
		return definition;

	}


	/**
	 * 把传递进来的参数转换成路由对象
	 */
	private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition g) {
		RouteDefinition definition = new RouteDefinition();
		definition.setId(g.getId());
		definition.setOrder(g.getOrder());

		// 设置断言
		List<PredicateDefinition> predicates = new ArrayList<>();
		List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = g.getPredicates();
		gatewayPredicateDefinitionList.forEach(gatewayPredicate -> {
			PredicateDefinition predicate = new PredicateDefinition();
			predicate.setArgs(gatewayPredicate.getArgs());
			predicate.setName(gatewayPredicate.getName());
			predicates.add(predicate);
		});
		definition.setPredicates(predicates);

		// 设置过滤器
		List<FilterDefinition> filters = new ArrayList<>();
		List<GatewayFilterDefinition> gatewayFilterDefinitionList = g.getFilters();
		gatewayFilterDefinitionList.forEach(gatewayFilter -> {
			FilterDefinition filter = new FilterDefinition();
			filter.setArgs(gatewayFilter.getArgs());
			filter.setName(gatewayFilter.getName());
			filters.add(filter);
		});
		definition.setFilters(filters);

		// 设置元数据
		definition.setMetadata(g.getMetadatas());

		//设置URI
		URI uri = null;
		if (g.getUri().startsWith("http")) {
			UriComponentsBuilder.fromHttpUrl(g.getUri()).build().toUri();
		} else {
			// uri为 lb://consumer-service 时使用下面的方法
			uri = URI.create(g.getUri());
		}
		definition.setUri(uri);
		return definition;

	}
}
