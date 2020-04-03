package com.czp.springcloud.service.impl;

import com.czp.springcloud.entity.CustomerRouteDefinition;
import com.czp.springcloud.mapper.RouteDefinitionMapper;
import com.czp.springcloud.service.RouteDefinitionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RouteDefinition)表服务实现类
 *
 * @author CZP
 * @since 2020-03-19 11:32:38
 */
@Service("routeDefinitionService")
public class RouteDefinitionServiceImpl implements RouteDefinitionService {
	@Resource
	private RouteDefinitionMapper routeDefinitionMapper;

	/**
	 * 通过ID查询单条数据
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	@Override
	public CustomerRouteDefinition queryById(Integer id) {
		return routeDefinitionMapper.queryById(id);
	}

	/**
	 * 通过路由Id查询单条数据
	 *
	 * @param routeId 路由Id
	 * @return 实例对象
	 */
	@Override
	public CustomerRouteDefinition queryByRouteId(String routeId) {
		return routeDefinitionMapper.queryByRouteId(routeId);
	}

	/**
	 * 查询多条数据
	 *
	 * @param offset 查询起始位置
	 * @param limit  查询条数
	 * @return 对象列表
	 */
	@Override
	public List<CustomerRouteDefinition> queryAllByLimit(int offset, int limit) {
		return routeDefinitionMapper.queryAllByLimit(offset, limit);
	}

	/**
	 * 新增数据
	 *
	 * @param customerRouteDefinition 实例对象
	 * @return 实例对象
	 */
	@Override
	public CustomerRouteDefinition insert(CustomerRouteDefinition customerRouteDefinition) {
		routeDefinitionMapper.insert(customerRouteDefinition);
		return customerRouteDefinition;
	}

	/**
	 * 修改数据
	 *
	 * @param customerRouteDefinition 实例对象
	 * @return 实例对象
	 */
	@Override
	public CustomerRouteDefinition update(CustomerRouteDefinition customerRouteDefinition) {
		routeDefinitionMapper.update(customerRouteDefinition);
		return queryById(customerRouteDefinition.getId());
	}

	/**
	 * 通过主键删除数据
	 *
	 * @param id 主键
	 * @return 是否成功
	 */
	@Override
	public boolean deleteById(Integer id) {
		return routeDefinitionMapper.deleteById(id) > 0;
	}

	/**
	 * 通过路由Id删除数据
	 *
	 * @param routeId 路由Id
	 * @return 影响行数
	 */
	@Override
	public boolean deleteByRouteId(String routeId) {
		return routeDefinitionMapper.deleteByRouteId(routeId) > 0;
	}


}