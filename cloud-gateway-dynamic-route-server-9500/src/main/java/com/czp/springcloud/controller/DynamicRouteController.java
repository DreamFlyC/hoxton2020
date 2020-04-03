package com.czp.springcloud.controller;

import com.czp.springcloud.def.GatewayFilterDefinition;
import com.czp.springcloud.def.GatewayPredicateDefinition;
import com.czp.springcloud.def.GatewayRouteDefinition;
import com.czp.springcloud.dynamic.DynamicRouteService;
import com.czp.springcloud.entities.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-17 15:27:49
 * @description :  动态路由实现，
 */

@Slf4j
@RestController
@RequestMapping("/route")
public class DynamicRouteController {

	private final DynamicRouteService dynamicRouteService;

	public DynamicRouteController(DynamicRouteService dynamicRouteService) {
		this.dynamicRouteService = dynamicRouteService;
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
	@DeleteMapping("/delete/{id}")
	public Result delete(@PathVariable("id") String id) {
		try {
			dynamicRouteService.delete(id);
		} catch (Exception e) {
			log.error("删除路由失败", e);
			return Result.fail().msg("删除路由失败");
		}
		return Result.ok().msg("删除路由成功");
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
