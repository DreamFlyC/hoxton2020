package com.czp.springcloud.config;

import com.alibaba.fastjson.JSON;
import com.czp.springcloud.entity.CustomerRouteDefinition;
import com.czp.springcloud.mapper.RouteDefinitionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-19 10:15:36
 * @description :
 */

@Slf4j
@Component
public class JdbcRouteDefinitionRepository implements RouteDefinitionRepository {

	@Resource
	RouteDefinitionMapper routeDefinitionMapper;

	@Override
	@SuppressWarnings("unchecked")
	public Flux<RouteDefinition> getRouteDefinitions() {
		List<CustomerRouteDefinition> customerRouteDefinitions = routeDefinitionMapper.findAll();
		List<RouteDefinition> routeDefinitions = new ArrayList<>();

		if (customerRouteDefinitions != null && customerRouteDefinitions.size() > 0) {
			customerRouteDefinitions.forEach(customerRouteDefinition -> {
				RouteDefinition routeDefinition = new RouteDefinition();
				routeDefinition.setId(customerRouteDefinition.getRouteId());
				if (!StringUtils.isEmpty(customerRouteDefinition.getPredicates())) {
					routeDefinition.setPredicates(JSON.parseArray(customerRouteDefinition.getPredicates(), PredicateDefinition.class));
				} else {
					log.warn("the predicates is empty,routeId:" + customerRouteDefinition.getRouteId());
					throw new IllegalArgumentException("Predicates may not be empty，routeId:" + customerRouteDefinition.getRouteId());
				}
				if (!StringUtils.isEmpty(customerRouteDefinition.getFilters())) {
					routeDefinition.setFilters(JSON.parseArray(customerRouteDefinition.getFilters(), FilterDefinition.class));
				} else {
					log.warn("the filters is empty,routeId:" + customerRouteDefinition.getRouteId());
					throw new IllegalArgumentException("Predicates may not be empty，routeId:" + customerRouteDefinition.getRouteId());
				}
				if (!StringUtils.isEmpty(customerRouteDefinition.getMetadatas())) {
					log.warn("the metadata is empty,routeId:" + customerRouteDefinition.getRouteId());
					routeDefinition.setMetadata(JSON.parseObject(customerRouteDefinition.getMetadatas(), Map.class));
				}
				routeDefinition.setUri(parseUri(customerRouteDefinition.getUri()));
				routeDefinition.setOrder(customerRouteDefinition.getOrder());
				routeDefinitions.add(routeDefinition);
			});
		}
		return Flux.fromIterable(routeDefinitions);
	}

	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		return route.flatMap(r -> {
			check(r);
			CustomerRouteDefinition customerRouteDefinition = new CustomerRouteDefinition();
			customerRouteDefinition.setRouteId(r.getId());
			customerRouteDefinition.setOrder(r.getOrder());
			customerRouteDefinition.setUri(r.getUri().toString());
			customerRouteDefinition.setFilters(JSON.toJSONString(r.getFilters()));
			customerRouteDefinition.setPredicates(JSON.toJSONString(r.getPredicates()));
			if (r.getMetadata() != null) {
				customerRouteDefinition.setMetadatas(JSON.toJSONString(r.getMetadata()));
				customerRouteDefinition.setEnable((Boolean) r.getMetadata().get("enable"));
				customerRouteDefinition.setDeleteFlag((Boolean) r.getMetadata().get("deleteFlag"));
			}
			routeDefinitionMapper.insert(customerRouteDefinition);
			log.info("存储动态路由成功," + customerRouteDefinition);
			return Mono.empty();
		});
	}

	private void check(RouteDefinition r) {
		if (StringUtils.isEmpty(r.getId())) {
			log.warn("路由id不能为空");
			throw new IllegalArgumentException("id may not be empty");
		}
		CustomerRouteDefinition definition = routeDefinitionMapper.queryByRouteId(r.getId());
		if (definition != null) {
			log.error("路由id必须唯一，routeId:" + r.getId());
			throw new IllegalArgumentException("id is already exist,routeId:" + r.getId());
		}
		if (r.getUri() == null) {
			log.error("路由URI不能为空，routeId:" + r.getId());
			throw new IllegalArgumentException("uri may not be empty，routeId:" + r.getId());
		}
		if (r.getPredicates() == null) {
			log.error("路由Predicates不能为空，routeId:" + r.getId());
			throw new IllegalArgumentException("Predicates may not be empty，routeId:" + r.getId());
		}
		if (r.getFilters() == null) {
			log.error("路由Filters不能为空，routeId:" + r.getId());
			throw new IllegalArgumentException("Filters may not be empty，routeId:" + r.getId());
		}
	}

	@Override
	public Mono<Void> delete(Mono<String> routeId) {
		return routeId.flatMap(id -> {
			if (id != null) {
				int result = routeDefinitionMapper.deleteByRouteId(id);
				if (result > 0) {
					log.info("删除路由成功，routeId:" + id);
					return Mono.empty();
				} else {
					log.warn("删除路由失败,routeId:" + id + ",路由信息不存在");
				}
			}
			return Mono.defer(() -> Mono.error(
					new NotFoundException("RouteDefinition not found: " + routeId)));
		});
	}


	private URI parseUri(String uriString) {
		//设置URI
		URI uri = null;
		if (uriString != null) {
			if (uriString.startsWith("http")) {
				UriComponentsBuilder.fromHttpUrl(uriString).build().toUri();
			} else {
				// uri为 lb://consumer-service 时使用下面的方法
				uri = URI.create(uriString);
			}
		} else {
			log.error("路由URI不能为空");
			throw new IllegalArgumentException("id may not be empty");
		}
		return uri;
	}


}
