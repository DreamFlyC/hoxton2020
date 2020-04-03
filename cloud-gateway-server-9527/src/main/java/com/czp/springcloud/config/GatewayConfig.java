package com.czp.springcloud.config;

import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AddRequestHeaderGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-15 19:25:16
 * @description :
 */
@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator customRoutes(RouteLocatorBuilder builder, AddRequestHeaderGatewayFilterFactory throttle) {
		AbstractNameValueGatewayFilterFactory.NameValueConfig requestHeader = new AbstractNameValueGatewayFilterFactory.NameValueConfig();
		requestHeader.setName("route-a");
		requestHeader.setValue("yes");
		return builder.routes()
				.route(r -> r.host("localhost:9527").and()
						.path("/guonei")
						.filters(
								f -> {
									f.stripPrefix(1)
											.addRequestHeader("X-Request-Id", "foobar")
											.redirect(HttpStatus.MOVED_PERMANENTLY.value(), "https://news.baidu.com");

									return f;
								})
						.uri("https://news.baidu.com/guonei")
						.id("custom-1")
				)
				.build();
	}

}
