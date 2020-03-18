package edu.xpu.catchdemo.repository;

import edu.xpu.catchdemo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoRepositoryTest {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void save(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("TestName");
        userInfo.setAge(25);
        assertNotNull(userInfoRepository.save(userInfo));
    }
}