package com.example.demo.service.impl;

import com.example.demo.entity.UserBasicInfo;
import com.example.demo.dao.UserBasicInfoDao;
import com.example.demo.service.UserBasicInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserBasicInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-02-16 20:10:48
 */
@Service("userBasicInfoService")
public class UserBasicInfoServiceImpl implements UserBasicInfoService {
    @Resource
    private UserBasicInfoDao userBasicInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param openId 主键
     * @return 实例对象
     */
    @Override
    public UserBasicInfo queryById(String openId) {
        return this.userBasicInfoDao.queryById(openId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserBasicInfo> queryAllByLimit(int offset, int limit) {
        return this.userBasicInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userBasicInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserBasicInfo insert(UserBasicInfo userBasicInfo) {
        this.userBasicInfoDao.insert(userBasicInfo);
        return userBasicInfo;
    }

    /**
     * 修改数据
     *
     * @param userBasicInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserBasicInfo update(UserBasicInfo userBasicInfo) {
        this.userBasicInfoDao.update(userBasicInfo);
        return this.queryById(userBasicInfo.getOpenId());
    }

    /**
     * 通过主键删除数据
     *
     * @param openId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String openId) {
        return this.userBasicInfoDao.deleteById(openId) > 0;
    }
}