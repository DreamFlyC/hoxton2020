package com.czp.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-17 15:14:45
 * @description : 动态路由服务
 */
@Slf4j
@Component
public class DynamicRouteService implements ApplicationEventPublisherAware {


	private final RouteDefinitionRepository routeDefinitionRepository;

	private ApplicationEventPublisher publisher;

	protected final RouteLocator routeLocator;

	public DynamicRouteService(@Qualifier("jdbcRouteDefinitionRepository") RouteDefinitionRepository routeDefinitionRepository, RouteLocator routeLocator) {
		this.routeDefinitionRepository = routeDefinitionRepository;
		this.routeLocator = routeLocator;
	}


	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	/**
	 * 增加路由
	 */
	public String add(RouteDefinition definition) {

		routeDefinitionRepository.save(Mono.just(definition)).subscribe();
		this.publisher.publishEvent(new RefreshRoutesEvent(this));
		return "success";
	}

	/**
	 * 更新路由
	 */
	public String update(RouteDefinition definition) {
		try {
			routeDefinitionRepository.save(Mono.just(definition)).subscribe();
			publisher.publishEvent(new RefreshRoutesEvent(this));
			return "success";
		} catch (Exception e) {
			return "update route fail";
		}
	}

	/**
	 * 删除路由
	 */
	public Mono<ResponseEntity<Object>> delete(String id) {
		Mono<ResponseEntity<Object>> responseEntityMono = routeDefinitionRepository.delete(Mono.just(id)).then(Mono.defer(
				() -> Mono.just(ResponseEntity.ok().build())
		))
				.onErrorResume(
						(t) -> t instanceof NotFoundException, (t)
								-> Mono.just(ResponseEntity.notFound().build())

				);
		responseEntityMono.subscribe();
		publisher.publishEvent(new RefreshRoutesEvent(this));
		return responseEntityMono;
	}

	public Flux<Map<String, Object>> get() {
		return this.routeLocator.getRoutes().map(this::serialize);

	}

	public Map<String, Object> serialize(Route route) {
		HashMap<String, Object> r = new HashMap<>();
		r.put("route_id", route.getId());
		r.put("uri", route.getUri().toString());
		r.put("order", route.getOrder());
		r.put("predicate", route.getPredicate().toString());
		if (!CollectionUtils.isEmpty(route.getMetadata())) {
			r.put("metadata", route.getMetadata());
		}

		ArrayList<String> filters = new ArrayList<>();

		for (int i = 0; i < route.getFilters().size(); i++) {
			GatewayFilter gatewayFilter = route.getFilters().get(i);
			filters.add(gatewayFilter.toString());
		}

		r.put("filters", filters);
		return r;
	}
}
