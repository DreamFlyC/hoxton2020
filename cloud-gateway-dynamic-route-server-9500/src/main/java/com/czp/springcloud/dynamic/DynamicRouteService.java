package com.czp.springcloud.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-17 15:14:45
 * @description : 动态路由服务
 */
@Slf4j
@Component
public class DynamicRouteService implements ApplicationEventPublisherAware {

	@Qualifier("redisRouteDefinitionRepository")
	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;

	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	/**
	 * 增加路由
	 */
	public String add(RouteDefinition definition) {

		routeDefinitionWriter.save(Mono.just(definition)).subscribe();
		this.publisher.publishEvent(new RefreshRoutesEvent(this));
		return "success";
	}

	/**
	 * 更新路由
	 */
	public String update(RouteDefinition definition) {
		try {
			delete(definition.getId());
		} catch (Exception e) {
			log.warn("update fail,not find route [routeId: " + definition.getId() + "]", e);
			return "update fail,not find route  [routeId: " + definition.getId() + "]";
		}
		try {
			routeDefinitionWriter.save(Mono.just(definition)).subscribe();
			publisher.publishEvent(new RefreshRoutesEvent(this));
			return "success";
		} catch (Exception e) {
			return "update route fail";
		}
	}

	/**
	 * 删除路由
	 */
	public String delete(String id) {
		routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(
				() -> Mono.just(ResponseEntity.ok().build())
		))
				.onErrorResume(
						(t) -> t instanceof NotFoundException, (t)
								-> Mono.just(ResponseEntity.notFound().build())

				).subscribe();
		publisher.publishEvent(new RefreshRoutesEvent(this));
		return "success";
	}
}
