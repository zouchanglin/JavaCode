package xpu.edu.elong.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xpu.edu.elong.dao.MemberInfoRepository;
import xpu.edu.elong.entity.MemberInfo;
import xpu.edu.elong.request.HttpRequestUtil;
import xpu.edu.elong.service.MemberService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final String url = "http://member.vip.elong.com:8080/member/getCardListByMobileAndEmail?mobile=";
    @Autowired
    private MemberInfoRepository memberInfoRepository;

    @Override
    public List<MemberInfo> getMamberData(String phoneNumber) {
        if(!StringUtils.isEmpty(phoneNumber)){
            //先去数据库找
            List<MemberInfo> allByMobile = memberInfoRepository.findAllByMobile(phoneNumber);
            if(allByMobile.size()!=0){
                //找到就返回
               return allByMobile;
            }else{
                //没找到，存
                String resultJson = HttpRequestUtil.sendPostRequest(url + phoneNumber);
                //{"code":"0","msg":"成功","data":[{"id":123277404,"cardNo":240000000095597608,"userId":"","name":"","nickName":"张阳","faceUrl":"http://pavo.elongstatic.com/i/ori/000g5oLa.jpg","password":"821fa6f9700e84d61","mobileType":0,"mobile":"13718935893","email":"","proxyId":"AP0011893","sex":1,"status":0,"memType":0,"createTime":"2016-10-28 18:52:47","opName":"172.21.34.16","_timestamp":"2019-01-08 18:24:33"},{"id":123277404,"cardNo":240000000095597608,"userId":"","name":"","nickName":"张阳","faceUrl":"http://pavo.elongstatic.com/i/ori/000g5oLa.jpg","password":"821fa6f9700e84d61","mobileType":0,"mobile":"13718935893","email":"","proxyId":"AP0011893","sex":1,"status":0,"memType":0,"createTime":"2016-10-28 18:52:47","opName":"172.21.34.16","_timestamp":"2019-01-08 18:24:33"}],"serverIp":"10.88.25.152","timestamp":1577022351339}
                JSONObject jsonObject = JSONObject.parseObject(resultJson);
                String data = jsonObject.getString("data");
                List<MemberInfo> memberInfos = JSONObject.parseArray(data, MemberInfo.class);
                //没找到就存，然后返回
                for(MemberInfo memberInfo: memberInfos){
                    memberInfoRepository.save(memberInfo);
                }
                return memberInfos;
            }
        }
        return new ArrayList<>();
    }
}