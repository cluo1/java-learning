package cn.mariojd.mini.program.util;

import cn.mariojd.mini.program.dto.WeChatUser;
import cn.mariojd.mini.program.vo.req.AuthorizeVO;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Map;

/**
 * @author jdq
 * @date 2017/12/15 16:02
 */
@Component
public class WeChatUtil {

    private final Logger logger = LoggerFactory.getLogger(WeChatUtil.class);

    @Value("${wx.url}")
    private String url;

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    /**
     * wx.login授权获得用户信息
     */
    public WeChatUser login(String code) {
        //请求参数
        String params = "appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        String requestURL = url + "?" + params;
        //发送请求
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(requestURL).build();
        Call call = okHttpClient.newCall(request);

        WeChatUser userInfo = null;
        try {
            Response res = call.execute();
            ResponseBody responseBody = res.body();
            Assert.notNull(responseBody, "登录失败");

            //解析响应内容（转换成json对象）
            String response = responseBody.string();
            Map<String, Object> userInfoMap = JsonUtils.json2object(response, Map.class, String.class, Object.class);
            logger.info(" userInfoMap is {} ", userInfoMap.toString());
            if (!userInfoMap.isEmpty()) {
                userInfo = new WeChatUser();
                userInfo.setOpenId(userInfoMap.get("openid").toString());
            }
        } catch (IOException e) {
            logger.error(" login get user info error {}", e);
        }
        return userInfo;
    }

    /**
     * wx.authorize授权获得用户信息
     *
     * @param authorizeVO
     * @return userInfo
     */
    public WeChatUser authorize(AuthorizeVO authorizeVO) {
        //请求参数
        String params = "appid=" + appId + "&secret=" + appSecret + "&js_code=" + authorizeVO.getCode() + "&grant_type=authorization_code";
        String requestURL = url + "?" + params;
        //发送请求
        logger.info(" request url is {} ", requestURL);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(requestURL).build();
        Call call = okHttpClient.newCall(request);

        WeChatUser userInfo = null;
        try {
            Response res = call.execute();
            ResponseBody responseBody = res.body();
            Assert.notNull(responseBody, "授权失败...");

            String response = responseBody.string();
            //解析响应内容（转换成json对象）
            Map<String, Object> map = JsonUtils.json2object(response, Map.class, String.class, Object.class);
            //获取会话密钥（session_key）
            logger.info(" small authorized response map is {} ", map);

            String sessionKey = map.get("session_key").toString();
            //对data进行AES解密
            String result = decrypt(authorizeVO.getData(), sessionKey, authorizeVO.getIv());

            if (!StringUtils.isEmpty(result)) {
                Map<String, Object> userInfoMap = JsonUtils.json2object(result, Map.class, String.class, Object.class);

                userInfo = new WeChatUser();
                userInfo.setOpenId(userInfoMap.get("openId").toString());
                userInfo.setNickname(userInfoMap.get("nickName").toString());
                userInfo.setGender(Integer.parseInt(userInfoMap.get("gender").toString()));
                userInfo.setAvatar(userInfoMap.get("avatarUrl").toString());
            }
        } catch (IOException e) {
            logger.error(" authorize get user info error {}", e);
        }
        logger.info(" userInfo is {} ", userInfo);
        return userInfo;
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES解密
     *
     * @param data 密文
     * @param key  秘钥
     * @param iv   偏移量
     * @return
     */
    private String decrypt(String data, String key, String iv) {
        //待加密数据,加密秘钥,偏移量
        byte[] dataByte = Base64.decodeBase64(data), keyByte = Base64.decodeBase64(key), ivByte = Base64.decodeBase64(iv);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                return new String(resultByte, "UTF-8");
            }
        } catch (Exception e) {
            logger.error(" decrypt error {} ", e);
        }
        return null;
    }

}
