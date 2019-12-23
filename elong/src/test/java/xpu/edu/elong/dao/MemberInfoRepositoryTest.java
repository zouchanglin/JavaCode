package xpu.edu.elong.dao;


import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xpu.edu.elong.entity.MemberInfo;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberInfoRepositoryTest {
    @Autowired
    private MemberInfoRepository repository;

    @Test
    public void save(){
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setId(1728292738L);
        memberInfo.setUserId("userID");
        memberInfo.set_timestamp("time");
        memberInfo.setCardNo(1212L);
        memberInfo.setFaceUrl("xxx");
        memberInfo.setName("xxx");
        memberInfo.setProxyId("xxx");
        memberInfo.setEmail("sasas@w");
        memberInfo.setCreateTime("123213");
        memberInfo.setMemType(1);
        memberInfo.setOpName("sasa");
        memberInfo.setMobile("sasa");
        memberInfo.setNickName("sasa");
        memberInfo.setPassword("sasa");
        memberInfo.setStatus(0);
        memberInfo.setSex(1);
        memberInfo.setMobileType(1);
        MemberInfo save = repository.save(memberInfo);
        assertNotNull(save);
    }


    @Test
    public void find(){
        List<MemberInfo> sasa = repository.findAllByMobile("sasa");
        assertEquals(2, sasa.size());
    }

    @Test
    public void saveJsonObj(){
        String json = "{\"code\":\"0\",\"msg\":\"成功\",\"data\":[{\"id\":123277403,\"cardNo\":240000000095597608,\"userId\":\"ABC\",\"name\":\"\",\"nickName\":\"张阳\",\"faceUrl\":\"http://pavo.elongstatic.com/i/ori/000g5oLa.jpg\",\"password\":\"821fa6f9700e84d61\",\"mobileType\":0,\"mobile\":\"13718935893\",\"email\":\"lhl@elong.com\",\"proxyId\":\"AP0011893\",\"sex\":1,\"status\":0,\"memType\":0,\"createTime\":\"2016-10-28 18:52:47\",\"opName\":\"172.21.34.16\",\"_timestamp\":\"2019-01-08 18:24:33\"},{\"id\":123277404,\"cardNo\":240000000095597609,\"userId\":\"DEF\",\"name\":\"\",\"nickName\":\"张阳\",\"faceUrl\":\"http://pavo.elongstatic.com/i/ori/000g5oLa.jpg\",\"password\":\"821fa6f9700e84d61\",\"mobileType\":0,\"mobile\":\"13718935893\",\"email\":\"123456@qq.com\",\"proxyId\":\"AP0011893\",\"sex\":1,\"status\":0,\"memType\":0,\"createTime\":\"2016-10-28 18:52:47\",\"opName\":\"172.21.34.17\",\"_timestamp\":\"2019-01-08 18:24:33\"},\n" +
                "{\"id\":123277405,\"cardNo\":240000000095597609,\"userId\":\"DEF\",\"name\":\"\",\"nickName\":\"张三\",\"faceUrl\":\"http://pavo.elongstatic.com/i/ori/000g5oLa.jpg\",\"password\":\"821fa6f9700e84d61\",\"mobileType\":0,\"mobile\":\"13718935899\",\"email\":\"123456@qq.com\",\"proxyId\":\"AP0011893\",\"sex\":1,\"status\":0,\"memType\":0,\"createTime\":\"2016-10-28 18:52:47\",\"opName\":\"172.21.34.17\",\"_timestamp\":\"2019-01-08 18:24:33\"}],\"serverIp\":\"10.88.25.152\",\"timestamp\":1577022351339}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        String data = jsonObject.getString("data");
        List<MemberInfo> memberInfos = JSONObject.parseArray(data, MemberInfo.class);
        for(MemberInfo memberInfo: memberInfos){
            MemberInfo save = repository.save(memberInfo);
            assertNotNull(save);
        }
    }
}