package xpu.edu.elong.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "member_info")
public class MemberInfo {
    @Id
    private Long id;
    private Long cardNo;
    private String userId;
    private String name;
    private String nickName;
    private String faceUrl;
    private String password;
    private Integer mobileType;
    private String mobile;
    private String email;
    private String proxyId;
    private Integer sex;
    private Integer status;
    private Integer memType;
    private String createTime;
    private String opName;
    private String _timestamp;
}