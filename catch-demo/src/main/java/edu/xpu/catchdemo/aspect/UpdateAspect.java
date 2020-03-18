package edu.xpu.catchdemo.aspect;

import edu.xpu.catchdemo.service.UserInfoService;
import edu.xpu.catchdemo.util.ResultVOUtil;
import edu.xpu.catchdemo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class UpdateAspect {
//    @Autowired
//    private UserInfoService userInfoService;
//
//    @CacheEvict(cacheNames = "users", key = "123")
//    @After("execution(public * edu.xpu.catchdemo.controller.AdminController.updateUserInfo(..))")
//    public ResultVO flushCatch(){
//        log.info("【刷新缓存】");
//        return ResultVOUtil.success(userInfoService.getAllUser());
//    }
}