package com.czp.springcloud.config;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-18 10:49:01
 * @description : 实现存储在redis下的动态路由,自定义redis存储路由配置信息，实现动态路由，将定义好的路由表信息通过此类读写到redis中
 * *                存在问题：每次请求都会调用getRouteDefinitions，当网关较多时，会影响请求速度，考虑放到本地Map中，使用消息通知Map更新。
 */
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

	public static final String GATEWAY_ROUTES = "gateway:routes";

	private final StringRedisTemplate stringRedisTemplate;

	private final Map<String, RouteDefinition> cacheMap = new ConcurrentHashMap<>();

	public RedisRouteDefinitionRepository(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	@Override
	public Flux<RouteDefinition> getRouteDefinitions() {
		if (cacheMap.values().size() > 0) {
			return Flux.fromIterable(cacheMap.values());
		}
		List<RouteDefinition> routeDefinitions = Lists.newArrayList();
		stringRedisTemplate.opsForHash().values(GATEWAY_ROUTES).forEach(r ->
				routeDefinitions.add(JSON.parseObject(r.toString(), RouteDefinition.class)));

		return Flux.fromIterable(routeDefinitions);
	}

	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		return route.flatMap(r -> {
			if (StringUtils.isEmpty(r.getId())) {
				return Mono.error(new IllegalArgumentException("id may not be empty"));
			}
			stringRedisTemplate.opsForHash().put(GATEWAY_ROUTES, r.getId(), JSON.toJSONString(r));
			cacheMap.put(r.getId(), r);
			return Mono.empty();
		});
	}

	@Override
	public Mono<Void> delete(Mono<String> routeId) {
		return routeId.flatMap(id -> {
			if (stringRedisTemplate.opsForHash().hasKey(GATEWAY_ROUTES, id)) {
				stringRedisTemplate.opsForHash().delete(GATEWAY_ROUTES, id);
				cacheMap.remove(id);
				return Mono.empty();
			}
			return Mono.defer(() -> Mono.error(
					new NotFoundException("RouteDefinition not found: " + routeId)));
		});
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", RedisRouteDefinitionRepository.class.getSimpleName() + "[", "]")
				.add("cacheMap=" + cacheMap)
				.toString();
	}

}
