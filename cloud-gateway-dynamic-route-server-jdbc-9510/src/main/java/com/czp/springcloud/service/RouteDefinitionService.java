package com.czp.springcloud.service;

import com.czp.springcloud.entity.CustomerRouteDefinition;
import java.util.List;

/**
 * (RouteDefinition)表服务接口
 *
 * @author CZP
 * @since 2020-03-19 11:32:38
 */
public interface RouteDefinitionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CustomerRouteDefinition queryById(Integer id);

    /**
     * 通过路由Id查询单条数据
     * @param routeId 路由Id
     * @return 实例对象
     */
    CustomerRouteDefinition queryByRouteId(String routeId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CustomerRouteDefinition> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param customerRouteDefinition 实例对象
     * @return 实例对象
     */
    CustomerRouteDefinition insert(CustomerRouteDefinition customerRouteDefinition);

    /**
     * 修改数据
     *
     * @param customerRouteDefinition 实例对象
     * @return 实例对象
     */
    CustomerRouteDefinition update(CustomerRouteDefinition customerRouteDefinition);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过路由Id删除数据
     *
     * @param routeId 路由Id
     * @return 影响行数
     */
    boolean deleteByRouteId(String routeId);

}