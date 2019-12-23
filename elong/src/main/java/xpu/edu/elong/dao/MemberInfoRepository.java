package xpu.edu.elong.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import xpu.edu.elong.entity.MemberInfo;

import java.util.List;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {
    List<MemberInfo> findAllByMobile(String mobile);
}
