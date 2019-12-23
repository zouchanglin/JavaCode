package xpu.edu.elong;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import xpu.edu.elong.entity.MemberInfo;

import java.util.List;

public class JsonTest {
    public static void main(String[] args) {
        String json = "{\"code\":\"0\",\"msg\":\"成功\",\"data\":[{\"id\":123277404,\"cardNo\":240000000095597608,\"userId\":\"\",\"name\":\"\",\"nickName\":\"张阳\",\"faceUrl\":\"http://pavo.elongstatic.com/i/ori/000g5oLa.jpg\",\"password\":\"821fa6f9700e84d61\",\"mobileType\":0,\"mobile\":\"13718935893\",\"email\":\"\",\"proxyId\":\"AP0011893\",\"sex\":1,\"status\":0,\"memType\":0,\"createTime\":\"2016-10-28 18:52:47\",\"opName\":\"172.21.34.16\",\"_timestamp\":\"2019-01-08 18:24:33\"},{\"id\":123277404,\"cardNo\":240000000095597608,\"userId\":\"\",\"name\":\"\",\"nickName\":\"张阳\",\"faceUrl\":\"http://pavo.elongstatic.com/i/ori/000g5oLa.jpg\",\"password\":\"821fa6f9700e84d61\",\"mobileType\":0,\"mobile\":\"13718935893\",\"email\":\"\",\"proxyId\":\"AP0011893\",\"sex\":1,\"status\":0,\"memType\":0,\"createTime\":\"2016-10-28 18:52:47\",\"opName\":\"172.21.34.16\",\"_timestamp\":\"2019-01-08 18:24:33\"}],\"serverIp\":\"10.88.25.152\",\"timestamp\":1577022351339}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        String data = jsonObject.getString("data");
        List<MemberInfo> memberInfos = JSONObject.parseArray(data, MemberInfo.class);
        for(MemberInfo memberInfo: memberInfos){
            System.out.println(memberInfo);
        }
    }
}
