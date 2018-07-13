package cn.mariojd.util;

import cn.mariojd.enums.MessageEnum;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 基于阿里云平台实现的邮件推送工具类
 *
 * @author Mario on 2017-03-02
 */
public class AliSendMailUtil {

    private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
    private static final int ALIDM_SMTP_PORT = 25;

    /**
     * 邮件推送
     */
    public static void sendMail(String to, MessageEnum messageEnum, String code) {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();

        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);

        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
        // props.put("mail.smtp.socketFactory.class",
        // "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.port", "465");

        // 发件人的账号
        props.put("mail.user", "xxx");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "xxx");

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        try {
            // 设置发件人
            message.setFrom(new InternetAddress(props.getProperty("mail.user")));
            // 设置收件人
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            // 设置邮件标题
            message.setSubject("来自马里奥(MARIO)的邮件");
            // 设置邮件的内容体
            String content = null;
            if (messageEnum.getCode().equals(101)) {
                content = messageEnum.getMessage()
                        + "<h3><a href='https://www.mariojd.cn/user/act/" + to + "/" + code
                        + "'>https://www.mariojd.cn/user/activation/" + to + "/" + code + "</a><h3>";
            } else if (messageEnum.getCode().equals(102)) {
                content = messageEnum.getMessage() + "<h3>" + code + "<h3>";
            } else if (messageEnum.getCode().equals(103)) {
                content = messageEnum.getMessage() + "<h3>" + code + "<h3>";
            }
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送邮件
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();// TODO
        } catch (MessagingException e) {
            e.printStackTrace();// TODO
        }
    }
}
