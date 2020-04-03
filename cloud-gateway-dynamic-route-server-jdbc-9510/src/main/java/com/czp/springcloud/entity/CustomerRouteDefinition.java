package com.czp.springcloud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (RouteDefinition)实体类
 *
 * @author CZP
 * @since 2020-03-19 11:32:37
 */
@Data
public class    CustomerRouteDefinition implements Serializable {
	private static final long serialVersionUID = 365341486478833041L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 路由id
	 */
	private String routeId;
	/**
	 * uri
	 */
	private String uri;
	/**
	 * 路由执行顺序
	 */
	private Integer order = 0;
	/**
	 * 断言json字符串
	 */
	private String predicates = "";
	/**
	 * 过滤器json字符串
	 */
	private String filters = "";
	/**
	 * 元数据json字符串
	 */
	private String metadatas;
	/**
	 * 是否启用 0未启用，1已启用
	 */
	private Boolean enable;
	/**
	 * 是否删除 0未删除，1已删除
	 */
	private Boolean deleteFlag;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;


}