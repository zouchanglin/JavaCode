package com.example.demo.dao;

import com.example.demo.entity.UserBasicInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserBasicInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-16 20:10:46
 */
public interface UserBasicInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param openId 主键
     * @return 实例对象
     */
    UserBasicInfo queryById(String openId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserBasicInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userBasicInfo 实例对象
     * @return 对象列表
     */
    List<UserBasicInfo> queryAll(UserBasicInfo userBasicInfo);

    /**
     * 新增数据
     *
     * @param userBasicInfo 实例对象
     * @return 影响行数
     */
    int insert(UserBasicInfo userBasicInfo);

    /**
     * 修改数据
     *
     * @param userBasicInfo 实例对象
     * @return 影响行数
     */
    int update(UserBasicInfo userBasicInfo);

    /**
     * 通过主键删除数据
     *
     * @param openId 主键
     * @return 影响行数
     */
    int deleteById(String openId);

}