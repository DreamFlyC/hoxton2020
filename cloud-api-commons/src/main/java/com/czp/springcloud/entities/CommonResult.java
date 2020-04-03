package com.czp.springcloud.entities;


import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : CZP
 * @version :
 * @date : Created in 2020-3-6 10:20:32
 * @description :
 */
@NoArgsConstructor
public class CommonResult extends HashMap<String, Object> {
	private static final long serialVersionUID = -8713837118340960775L;

	public CommonResult code(HttpStatus status) {
		this.put("code", status.value());
		return this;
	}

	public CommonResult(boolean ok) {
		if (ok) {
			ok();
		} else {
			fail();
		}
	}

	public CommonResult msg(String msg) {
		this.put("msg", msg);
		return this;
	}

	public CommonResult data(Object data) {
		this.put("data", data);
		return this;
	}

	public CommonResult ok() {
		this.code(HttpStatus.OK);
		this.msg("请求成功");
		this.data(null);
		return this;
	}

	public CommonResult fail() {
		this.code(HttpStatus.INTERNAL_SERVER_ERROR);
		this.msg("请求失败");
		this.data(null);
		return this;
	}

	@Override
	public CommonResult put(String key, Object value) {
		List<Object> list;
		if (this.containsKey(key) && this.get(key) != null) {
			list = new ArrayList<>();
			if (this.get(key) instanceof List) {
				list.addAll((ArrayList) this.get(key));
			} else {
				list.add(this.get(key));
			}
			list.add(value);
			super.put(key, list);
		} else {
			super.put(key, value);
		}
		return this;
	}
}
