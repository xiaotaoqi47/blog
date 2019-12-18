package com.scs.web.blog.util;//package com.scs.web.blog.util;
//
//import com.sun.mail.util.MailSSLSocketFactory;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.security.GeneralSecurityException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
//
///**
// * @ClassName EmailUtil
// * @Description TODO
// * @Author mq_xu
// * @Date 2019/11/26
// **/
//public class EmailUtil {
//    public static void main(String[] args) throws MessagingException, GeneralSecurityException {
//
//        Properties props = new Properties();
//        // 开启debug调试
//        props.setProperty("mail.debug", "true");
//        // 发送服务器需要身份验证
//        props.setProperty("mail.smtp.auth", "true");
//        // 设置邮件服务器主机名
//        props.setProperty("mail.host", "smtp.qq.com");
//        // 发送邮件协议名称
//        props.setProperty("mail.transport.protocol", "smtp");
//        // 开启SSL加密，否则会失败
//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.ssl.socketFactory", sf);
//        // 创建session
//        Session session = Session.getInstance(props);
//        // 创建邮件
//        Message msg = new MimeMessage(session);
//        // 设置标题
//        msg.setSubject("测试邮件");
//        // 编辑内容
//        // 设置内容
//        String content = "使用Java Mail发送的测试邮件\n" +
//                "来自许莫淇" +
//                "\n时间: " + getStringDate();
//        msg.setText(content);
//        // 发送的邮箱地址
//        msg.setFrom(new InternetAddress("16422802@qq.com"));
//        // 通过session得到transport对象
//        Transport transport = session.getTransport();
//        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
//        transport.connect("smtp.qq.com", "16422802@qq.com", "jfxsczbxbsjvcbag");
//        // 发送邮件
//        transport.sendMessage(msg, new Address[]{new InternetAddress("2731964526@qq.com")});
//        transport.close();
//    }
//
//    /**
//     * 获取当前时间
//     *
//     * @return String
//     */
//    private static String getStringDate() {
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return formatter.format(currentTime);
//    }
//
//}
