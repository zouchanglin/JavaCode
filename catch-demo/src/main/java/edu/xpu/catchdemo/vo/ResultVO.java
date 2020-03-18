package edu.xpu.catchdemo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = -2388019149388748082L;

    private Integer code;

    private String msg;

    private T data;
}