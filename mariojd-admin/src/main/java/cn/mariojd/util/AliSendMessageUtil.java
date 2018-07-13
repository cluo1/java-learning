package cn.mariojd.util;

import cn.mariojd.enums.MessageEnum;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于阿里云平台短信服务所实现的工具类
 *
 * @author Mario
 */
public class AliSendMessageUtil {

    /**
     * 发送短信
     */
    public static void sendMessage(String to, String code, MessageEnum messageEnum) {
        String host = "http://sms.market.alicloudapi.com";
        String path = "/singleSendSms";
        String method = "GET";
        Map<String, String> headers = new HashMap<String, String>();
		//APPCODE 
        headers.put("Authorization", "APPCODE xxx");
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ParamString", "{\"code\":\"" + code + "\"}");
        querys.put("RecNum", to);
        //SignName
        querys.put("SignName", "xxx");
        //TemplateCode
        querys.put("TemplateCode", "xxx");
        try {
            HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
            // 获取response的body
        } catch (Exception e) {
            e.printStackTrace();//TODO
        }
    }

}
