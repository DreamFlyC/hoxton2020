package com.czp.springcloud.mapper;

import com.czp.springcloud.entity.CustomerRouteDefinition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (RouteDefinition)表数据库访问层
 *
 * @author CZP
 * @since 2020-03-19 11:32:39
 */
@Mapper
public interface RouteDefinitionMapper {

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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CustomerRouteDefinition> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param customerRouteDefinition 实例对象
     * @return
     */
    List<CustomerRouteDefinition> queryAll(CustomerRouteDefinition customerRouteDefinition);

    /**
     * @return 对象列表
     */
    List<CustomerRouteDefinition> findAll();

    /**
     * 新增数据
     *
     * @param customerRouteDefinition 实例对象
     * @return 影响行数
     */
    int insert(CustomerRouteDefinition customerRouteDefinition);

    /**
     * 修改数据
     *
     * @param customerRouteDefinition 实例对象
     * @return 影响行数
     */
    int update(CustomerRouteDefinition customerRouteDefinition);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过路由Id删除数据
     *
     * @param routeId 路由Id
     * @return 影响行数
     */
    int deleteByRouteId(String routeId);

}