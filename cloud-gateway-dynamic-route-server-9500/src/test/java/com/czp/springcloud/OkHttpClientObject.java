package com.czp.springcloud;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-17 17:22:02
 * @description : 创建线程安全的okHttpClient单例
 */
public enum OkHttpClientObject {

	CLIENT;

	private OkHttpClient clientInstance;

	OkHttpClientObject() {
		int connectTimeout = 10;
		int writeTimeout = 10;
		int readTimeout = 30;
		clientInstance = new OkHttpClient.Builder()
				.connectTimeout(connectTimeout, TimeUnit.SECONDS)
				.writeTimeout(writeTimeout, TimeUnit.SECONDS)
				.readTimeout(readTimeout, TimeUnit.SECONDS)
				.retryOnConnectionFailure(true)
				.build();
	}

	public OkHttpClient getInstance() {
		return clientInstance;
	}
}