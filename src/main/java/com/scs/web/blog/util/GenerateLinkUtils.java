package com.scs.web.blog.util;

import com.scs.web.blog.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @ClassName GenerateLinkUtils
 * @Description TODO
 * @Author mq_xu
 * @Date 2019/11/26
 **/
public class GenerateLinkUtils {
    private static final String CHECK_CODE = "checkCode";

    public static String generateActivateLink(User user) {
        return "http://localhost:8080/api/user/active?id="
                + user.getId() + "&" + CHECK_CODE + "=" + generateCheckcode(user);
    }


    /**
     * 生成校验码，用户名+UUID唯一标识符，为安全把他们加密发送
     *
     * @param user
     * @return
     */
    private static String generateCheckcode(User user) {
        String nickname = user.getNickname();
        String randomCode = UUID.randomUUID().toString();
        return md5(nickname + ":" + randomCode);
    }


    private static String md5(String string) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("md5");
            md.update(string.getBytes());
            byte[] md5Bytes = md.digest();
            return bytes2Hex(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.err.println("md5这里出错了");
        }
        return null;
    }

    private static String bytes2Hex(byte[] byteArray) {
        StringBuilder strBuf = new StringBuilder();
        for (byte b : byteArray) {
            if (b >= 0 && b < 16) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(b & 0xFF));
        }
        return strBuf.toString();
    }

}
