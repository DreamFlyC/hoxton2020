package com.czp.springcloud;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-17 17:03:26
 * @description :
 */
@Slf4j
class DynamicRouteTests9500 {

	private static OkHttpClient okHttpClient;

	private final String URL_PREFIX = "http://localhost:9500/route";

	static {
		okHttpClient = OkHttpClientObject.CLIENT.getInstance();
	}

	public static void main(String[] args) throws IOException {
		DynamicRouteTests9500 dynamicRouteTests = new DynamicRouteTests9500();
		String routeId = "CLOUD-PAYMENT-SERVICE";
//		dynamicRouteTests.testUpdate();
		dynamicRouteTests.testDelete(routeId);
	}

	/**
	 * 测试修改动态路由
	 */
	void testUpdate() throws IOException {
		String uri = "/update";
		Resource resource = new ClassPathResource("updateData.json");
		String data = inputstream2String(resource.getInputStream());
		System.out.println("读取的updateData.json为：" + data);

		/*RequestBody requestBody = new RequestBody() {
			@Override
			public MediaType contentType() {
				return MediaType.parse("application/json");
			}

			@Override
			public void writeTo(BufferedSink bufferedSink) throws IOException {
				bufferedSink.writeUtf8(data);
			}
		};*/

		final Request request = new Request.Builder()
				.url(URL_PREFIX + uri)
				.put(RequestBody.create(MediaType.parse("application/json"),data))
				.build();
		Call call = okHttpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				log.error("添加动态路由失败：", e);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				assert response.body() != null;
				log.info("添加成功，返回数据：" + response.body().string());
			}
		});
	}

	void testDelete(String routeId) throws IOException {
		String uri = "/delete/";
		final Request request = new Request.Builder()
				.url(URL_PREFIX + uri + routeId)
				.delete()
				.build();
		Call call = okHttpClient.newCall(request);
		Response response = call.execute();
		assert response.body() != null;
		log.info(response.body().string());
	}


	/**
	 * 将inputStream转为tring
	 */
	public static String inputstream2String(InputStream inputStream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toString(StandardCharsets.UTF_8.name());
	}

	public static String readFileAsString(String fileName) {
		String msg = "";
		InputStreamReader input;
		try {


		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return msg;
	}

}
