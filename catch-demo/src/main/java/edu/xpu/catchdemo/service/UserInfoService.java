package edu.xpu.catchdemo.service;

import edu.xpu.catchdemo.entity.UserInfo;
import edu.xpu.catchdemo.vo.UserInfoVO;

import java.util.List;

public interface UserInfoService {
    /**
     * 获取用户信息列表
     */
    List<UserInfoVO> getAllUser();

    /**
     * 修改用户信息
     */
    UserInfo update(Integer id, Integer age);
}
