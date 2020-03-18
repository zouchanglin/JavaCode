package edu.xpu.catchdemo.service.impl;

import edu.xpu.catchdemo.entity.UserInfo;
import edu.xpu.catchdemo.repository.UserInfoRepository;
import edu.xpu.catchdemo.service.UserInfoService;
import edu.xpu.catchdemo.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public List<UserInfoVO> getAllUser() {
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        List<UserInfoVO> list = new ArrayList<>();
        UserInfoVO userInfoVO;
        for(UserInfo userInfo: userInfoList){
            userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(userInfo, userInfoVO);
            list.add(userInfoVO);
        }
        return list;
    }

    @Override
    public UserInfo update(Integer id, Integer age) {
        Optional<UserInfo> userInfoOpt = userInfoRepository.findById(id);
        if(userInfoOpt.isPresent()){
            UserInfo userInfo = userInfoOpt.get();
            userInfo.setAge(age);
            return userInfoRepository.save(userInfo);
        }else{
            return null;
        }
    }
}
