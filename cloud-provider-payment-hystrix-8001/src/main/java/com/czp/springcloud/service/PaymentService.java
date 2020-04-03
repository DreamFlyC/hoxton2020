package com.czp.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-11 14:27:21
 * @description :
 */
@Service
public class PaymentService {

	//正常情况
	public String paymentOK(Integer id) {
		return "线程池：" + Thread.currentThread().getName() + " payment_OK,id:" + id + "\t" + "哈哈哈";
	}

	/**
	 * execution.isolation.strategy：该属性用来设置HystrixCommand.run()执行的隔离策略。默认为THREAD。
	 * execution.isolation.thread.timeoutInMilliseconds：该属性用来配置HystrixCommand执行的超时时间，单位为毫秒。
	 * execution.timeout.enabled：该属性用来配置HystrixCommand.run()的执行是否启用超时时间。默认为true。
	 * execution.isolation.thread.interruptOnTimeout：该属性用来配置当HystrixCommand.run()执行超时的时候是否要它中断。
	 * execution.isolation.thread.interruptOnCancel：该属性用来配置当HystrixCommand.run()执行取消时是否要它中断。
	 * execution.isolation.semaphore.maxConcurrentRequests：当HystrixCommand命令的隔离策略使用信号量时，该属性用来配置信号量的大小。当最大并发请求达到该设置值时，后续的请求将被拒绝。
	 */
	// 耗时
	@HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
	})
	public String paymentTimeout(Integer id) {
		int timeout = 2;
//		int i = 10/0;
		try {
			TimeUnit.SECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "线程池：" + Thread.currentThread().getName() + " paymentTimeout,id:" + id + "\t" + "哈哈哈,耗时（秒）：" + timeout;
	}

	public String paymentTimeoutHandler(Integer id){
		return "线程池：" + Thread.currentThread().getName() + " paymentTimeoutHandler,id:" + id + "\t" + "哈哈哈,我降级啦！！！";
	}
}
