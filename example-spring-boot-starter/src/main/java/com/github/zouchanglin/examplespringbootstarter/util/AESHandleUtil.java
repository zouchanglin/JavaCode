package com.github.zouchanglin.examplespringbootstarter.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class AESHandleUtil {
    /**
     * AES加密字符串
     *
     * @param content 需要被加密的字符串
     * @param password 加密需要的密码
     * @param length 初始化秘钥随机串的长度
     * @return 密文
     */
    public static byte[] encrypt(String content, String password, Integer length) {
        try {
            // 创建AES的Key生产者
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // 利用用户密码作为随机数初始化出
            keyGenerator.init(length, new SecureRandom(password.getBytes()));
            // 加密没关系，SecureRandom是生成安全随机数序列
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            return cipher.doFinal(byteContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密AES加密过的字符串
     *
     * @param content AES加密过过的内容
     * @param password 加密时的密码
     * @param length 初始化秘钥随机串的长度
     * @return 明文
     */
    public static byte[] decrypt(byte[] content, String password, Integer length) {
        try {
            // 创建AES的Key生产者
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(length, new SecureRandom(password.getBytes()));
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 明文
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
