package com.czp.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-16 14:39:36
 * @description : 自定义全局过滤器
 */

@Slf4j
//@Component
public class MyLogGlobalFilter implements GlobalFilter, Ordered {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String uri = exchange.getRequest().getURI().toString();
		log.info("*********************进入自定义全局GlobalFilter,当前请求URI:" +uri);
		String uname = exchange.getRequest().getQueryParams().getFirst("uname");
		if (uname == null) {
			log.error("**********用户名不存在，非法用户，禁止登录！/(ㄒoㄒ)/~~");
			if (!uri.contains("?")) {
				exchange.addUrlTransformer(url -> url + "?uname=czp");
				String transformUrl = exchange.transformUrl(uri);
				log.error("**********重写url:" + transformUrl);
			}
			exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
			return exchange.getResponse().setComplete();
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
