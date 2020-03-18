package edu.xpu.catchdemo.controller;

import com.alibaba.fastjson.JSONObject;
import edu.xpu.catchdemo.service.UserInfoService;

import edu.xpu.catchdemo.util.ResultVOUtil;
import edu.xpu.catchdemo.vo.ResultVO;
import edu.xpu.catchdemo.vo.UserInfoVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class AdminController {

    private final UserInfoService userInfoService;

    public AdminController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("list")
    @Cacheable(cacheNames = "users", key = "123")
    public ResultVO getUserList(){
        List<UserInfoVO> allUser = userInfoService.getAllUser();
        return ResultVOUtil.success(allUser);
    }

    //@CachePut(cacheNames = "users", key = "123") //访问这个方法之后，会更新缓存
    @GetMapping("update")
    @CacheEvict(cacheNames = "users", key = "123") //访问这个方法之后，会清除缓存
    public String updateUserInfo(Integer id, Integer age){
        userInfoService.update(id, age);
        return "success";
    }
}