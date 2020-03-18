package edu.xpu.catchdemo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = -7157555447670464208L;
    private Integer id;

    private String name;

    private Integer age;
}
