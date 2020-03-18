package com.example.demo.controller;

import com.example.demo.entity.UserBasicInfo;
import com.example.demo.service.UserBasicInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserBasicInfo)表控制层
 *
 * @author makejava
 * @since 2020-02-16 20:10:48
 */
@RestController
@RequestMapping("userBasicInfo")
public class UserBasicInfoController {
    /**
     * 服务对象
     */
    @Resource
    private UserBasicInfoService userBasicInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserBasicInfo selectOne(String id) {
        return this.userBasicInfoService.queryById(id);
    }

}