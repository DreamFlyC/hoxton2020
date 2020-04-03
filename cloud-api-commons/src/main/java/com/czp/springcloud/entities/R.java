package com.czp.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-7 10:40:48
 * @description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {

	private Integer code;
	private String msg;
	private T data;

	public R<T> code(HttpStatus status) {
		this.code = status.value();
		return this;
	}

	public R<T> code(Integer code) {
		this.code = code;
		return this;
	}

	public R<T> msg(String msg) {
		this.msg = msg;
		return this;
	}

	public R<T> data(T data) {
		this.data = data;
		return this;
	}

	public static<T> R<T> ok() {
		return new R<T>().code(HttpStatus.OK).msg("请求成功");
	}

	public static<T> R<T> ok(T data) {
		return new R<T>().code(HttpStatus.OK).msg("请求成功").data(data);
	}

	public static<T> R<T> fail() {
		return new R<T>().code(HttpStatus.INTERNAL_SERVER_ERROR).msg("请求失败");
	}

	public static<T> R<T> fail(T data) {
		return new R<T>().code(HttpStatus.INTERNAL_SERVER_ERROR).msg("请求失败").data(data);
	}

}
