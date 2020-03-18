package com.example.demo.service;

import com.example.demo.entity.UserBasicInfo;
import java.util.List;

/**
 * (UserBasicInfo)表服务接口
 *
 * @author makejava
 * @since 2020-02-16 20:10:47
 */
public interface UserBasicInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param openId 主键
     * @return 实例对象
     */
    UserBasicInfo queryById(String openId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserBasicInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userBasicInfo 实例对象
     * @return 实例对象
     */
    UserBasicInfo insert(UserBasicInfo userBasicInfo);

    /**
     * 修改数据
     *
     * @param userBasicInfo 实例对象
     * @return 实例对象
     */
    UserBasicInfo update(UserBasicInfo userBasicInfo);

    /**
     * 通过主键删除数据
     *
     * @param openId 主键
     * @return 是否成功
     */
    boolean deleteById(String openId);

}