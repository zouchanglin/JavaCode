package edu.xpu.catchdemo.repository;

import edu.xpu.catchdemo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
}
