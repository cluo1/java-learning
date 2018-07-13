package cn.mariojd.mini.program.exception;

/**
 * @author luwei
 */
public class RedisKeyPrefix {

    public static String phoneCaptchaUser(String phone) {
        return "user.phone.captcha:" + phone;
    }

    public static String buildTokenToUserId(String token) {
        return "user.token.uid:" + token;
    }

    public static String buildUserIdToToken(Integer userId) {
        return "user.uid.token:" + userId;
    }

}
