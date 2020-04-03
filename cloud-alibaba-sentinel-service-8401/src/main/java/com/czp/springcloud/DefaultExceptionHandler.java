package com.czp.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author      : CZP
 * @date        : Created in 2020-3-31 9:42:28
 * @description : 
 * @version     : 
 */

@Slf4j
 @Component
public class DefaultExceptionHandler {

	public static String fallbackHandler(Throwable e) {
		log.info("异常信息：", e);
		return "hotKey的异常处理方法hotkeyHandler------Exception:" + e;
	}
}
