package com.github.zouchanglin.examplespringbootstarter.service;

import com.github.zouchanglin.examplespringbootstarter.util.AESHandleUtil;

public class AESHandleService {
    private final Integer length;

    public AESHandleService(Integer length) {
        this.length = length;
    }

    public byte[] encrypt(String content, String password) {
        return AESHandleUtil.encrypt(content, password, length);
    }

    public byte[] decrypt(byte[] content, String password) {
        return AESHandleUtil.decrypt(content, password, length);
    }
}