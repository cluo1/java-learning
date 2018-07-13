package cn.mariojd.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Token验证工具类
 *
 * @author Mario
 *
 */
public class WeixinTokenUtil {

    // 接口配置信息中的Token
    private static final String token = "Mario";

    /**
     * 加密/校验签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return boolean检验结果
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] { token, timestamp, nonce };
        // 1.将token、timestamp、nonce三个参数拼接成数组进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            // 2.将三个参数字符串拼接成一个字符串
            content.append(arr[i]);
        }
        // 3.进行sha1加密
        String temp = getSha1(content.toString());
        // 4.将获得加密后的字符串与signature对比，然后返回Token验证结果
        return temp.equalsIgnoreCase(signature);
    }

    /**
     * sha1加密方法
     *
     * @param str
     * @return String
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes("UTF-8"));
            byte messageDigest[] = digest.digest();
            int j = messageDigest.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = messageDigest[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
