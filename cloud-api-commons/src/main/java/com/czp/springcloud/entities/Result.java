package com.czp.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-7 10:40:48
 * @description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

	private Integer code;
	private String msg;

//	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Map<String, Object> data = new HashMap<>();

	/*@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(value = "data")
	private Object obj;*/

	public Result code(HttpStatus status) {
		this.code = status.value();
		return this;
	}

	public Result code(Integer code) {
		this.code = code;
		return this;
	}

	public Result msg(String msg) {
		this.msg = msg;
		return this;
	}

	public Result data(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public Result data(Object data) {
//		this.obj = data;
		this.data.put("", data);
		return this;
	}

	public Result data(Map<String, Object> map) {
		this.setData(map);
		return this;
	}

	public static Result ok() {
		return new Result().code(HttpStatus.OK).msg("请求成功");
	}

	public static Result ok(String key, Object value) {
		return new Result().code(HttpStatus.OK).msg("请求成功").data(key, value);
	}

	public static Result ok(Map<String, Object> data) {
		return new Result().code(HttpStatus.OK).msg("请求成功").data(data);
	}

	public static Result fail() {
		return new Result().code(HttpStatus.INTERNAL_SERVER_ERROR).msg("请求失败");
	}

	public static Result fail(String key, Object value) {
		return new Result().code(HttpStatus.INTERNAL_SERVER_ERROR).msg("请求失败").data(key, value);
	}

	public static Result fail(Map<String, Object> data) {
		return new Result().code(HttpStatus.INTERNAL_SERVER_ERROR).msg("请求失败").data(data);
	}

}
