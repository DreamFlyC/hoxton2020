package com.czp.springcloud.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-17 11:36:12
 * @description :
 */
@Slf4j
//@Component
public class GatewayService implements ApplicationEventPublisherAware, CommandLineRunner {

	@Autowired
	private RedisRouteDefinitionRepository routeDefinitionWriter;

	private ApplicationEventPublisher publisher;

	@Value("${server.port:9527}")
	private String port;

	@Override
	public void run(String... args) throws Exception {
		this.save();
	}

	private void save() {
		//从数据库拿到路由配置
		log.info("<=================网关配置信息=================>");

		RouteDefinition definition = new RouteDefinition();
		definition.setId("test");
		URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port).build().toUri();
		definition.setUri(uri);

		//定义第一个断言  Path
		PredicateDefinition path = new PredicateDefinition();
		path.setName("Path");
		Map<String, String> pathParams = new HashMap<>(8);
		pathParams.put("pattern", "/jd");
		path.setArgs(pathParams);

		//定义第二个断言  Method
		PredicateDefinition method = new PredicateDefinition();
		method.setName("Method");
		Map<String, String> methodParams = new HashMap<>(8);
		methodParams.put("pattern", "/jd");
		method.setArgs(methodParams);

		//定义Filter
		FilterDefinition filter1 = new FilterDefinition();
		filter1.setName("AddRequestHeader");
		Map<String, String> filter1Params = new HashMap<>(8);
		//该_genkey_前缀是固定的，见org.springframework.cloud.gateway.support.NameUtils类
		filter1Params.put("_genkey_0", "header");
		filter1Params.put("_genkey_1", "addHeader");
		filter1.setArgs(filter1Params);

		FilterDefinition filter2 = new FilterDefinition();
		filter2.setName("AddRequestParameter");
		Map<String, String> filter2Params = new HashMap<>(8);
		filter2Params.put("_genkey_0", "param");
		filter2Params.put("_genkey_1", "addParam");
		filter2.setArgs(filter2Params);

		definition.setFilters(Arrays.asList(filter1, filter2));


		this.publisher.publishEvent(new RefreshRoutesEvent(this));

	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	public void deleteRoute(String routeId) {
		routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
	}
}
