package com.czp.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.czp.springcloud.DefaultExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-30 10:15:38
 * @description :
 */

@Slf4j
@RestController
public class SentinelServiceController {

	@Value("${server.port}")
	public String port;

	@GetMapping("/testA")
	public String info1() {
		log.info("testA我进来啦 \t" + Thread.currentThread().getName());
		return "testA port is :" + port;
	}

	@GetMapping("/testB")
	public String info2() {
		log.info("testB我进来啦 \t" + Thread.currentThread().getName());
		return "testB port is :" + port;
	}

	@GetMapping("/testE")
	@SentinelResource(value = "testE", fallback = "fallbackHandler", fallbackClass = DefaultExceptionHandler.class,
			exceptionsToTrace = {IOException.class, IllegalArgumentException.class,ArithmeticException.class})
	public String testE() {
		log.info("testE 测试异常");
		int age = 10 / 0;
		return "----------testE 测试异常数";
	}

	/**
	 * 关于@SentinelResource注解最主要的两个用法：限流控制和熔断降级的具体使用案例介绍完了。另外，该注解还有一些其他更精细化的配置，比如忽略某些异常的配置、默认降级函数等等，具体可见如下说明：
	 * <p>
	 * value：资源名称，必需项（不能为空）
	 * entryType：entry 类型，可选项（默认为 EntryType.OUT）
	 * blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项。blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，
	 * 参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass
	 * 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
	 * fallback：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了exceptionsToIgnore里面排除掉的异常类型）进行处理。
	 * fallback 函数签名和位置要求：
	 * 返回值类型必须与原函数返回值类型一致；
	 * 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
	 * fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
	 * defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常
	 * （除了exceptionsToIgnore里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：
	 * 返回值类型必须与原函数返回值类型一致；
	 * 方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
	 * defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
	 * exceptionsToIgnore（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。
	 * > 注：1.6.0 之前的版本 fallback 函数只针对降级异常（DegradeException）进行处理，不能针对业务异常进行处理。
	 * <p>
	 * 特别地，若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。若未配置 blockHandler、fallback 和 defaultFallback，
	 * 则被限流降级时会将 BlockException 直接抛出。
	 */
	@GetMapping("/hotkey")
	@SentinelResource(value = "hotkey", blockHandler = "hotkeyHandler")
	public String hotKey(@RequestParam(value = "p1", required = false) String p1,
	                     @RequestParam(value = "p2", required = false) String p2) {

		return "-----------TestHotKey";
	}

	public String hotkeyHandler(String p1, String p2, BlockException exception) {

		log.info("异常信息：", exception);
		return "hotKey的错误处理方法hotkeyHandler------BlockException";
	}


}
