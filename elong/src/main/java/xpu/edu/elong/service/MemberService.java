package xpu.edu.elong.service;

import xpu.edu.elong.entity.MemberInfo;

import java.util.List;

public interface MemberService {
    //根据手机号查询卡号，把卡号的属性在页面列出来，先从MySQL查，查不到再从接口，最后再写MySQL
    List<MemberInfo> getMamberData(String phoneNumber);
}