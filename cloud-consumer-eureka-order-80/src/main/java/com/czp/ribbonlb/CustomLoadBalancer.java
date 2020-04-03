package com.czp.ribbonlb;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-9 18:54:54
 * @description : 
 * @version     : 
 */
@Slf4j
public class CustomLoadBalancer extends AbstractLoadBalancerRule {
	// total = 0 // 当total==5以后，我们指针才能往下走，
	// index = 0 // 当前对外提供服务的服务器地址，
	// total需要重新置为零，但是已经达到过一个5次，我们的index = 1
	// 分析：我们5次，但是微服务只有8001 8002 8003 三台，OK？

	private int total = 0; 	    // 总共被调用的次数，目前要求每台被调用5次
	private int currentIndex = 0;	    // 当前提供服务的机器号

	public Server choose(ILoadBalancer lb, Object key){
		log.info("进入自定义CustomLoadBalancer#choose方法");
		if (lb == null) {
			return null;
		}
		Server server = null;

		while (true) {
			if (Thread.interrupted()) {
				return null;
			}
			List<Server> upList = lb.getReachableServers();  //激活可用的服务
			List<Server> allList = lb.getAllServers();  //所有的服务

			int serverCount = allList.size();
			if (serverCount == 0) {
				return null;
			}

			if(total < 5){
				server = upList.get(currentIndex);
				total++;
			}else {
				total = 0;
				currentIndex++;
				if(currentIndex >= upList.size()){
					currentIndex = 0;
				}
			}
			if (server == null) {
				Thread.yield();
				continue;
			}

			if (server.isAlive()) {
				return (server);
			}

			// Shouldn't actually happen.. but must be transient or a bug.
			server = null;
			Thread.yield();
		}
	}
	@Override
	public Server choose(Object key){
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig){
	}
}
