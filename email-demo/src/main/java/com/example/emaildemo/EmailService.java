package com.example.emaildemo;

public interface EmailService {
    /**
     * 发送简单文本内容
     * @param to 发件人
     * @param subject 主题
     * @param text 内容
     */
    void sendSimpleMessage(String to, String subject, String text);
}
