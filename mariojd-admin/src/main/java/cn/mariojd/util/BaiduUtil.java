package cn.mariojd.util;


import net.sf.json.JSONObject;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by Mario
 * 百度翻译工具类
 */
public class BaiduUtil {

    /**
     * 百度翻译APPID
     */
    private static final String APPID = "xxx";
    /**
     * 百度翻译APPKEY
     */
    private static final String APPKEY = "xxx";


    /**
     * 翻译功能
     *
     * @param sourceText 待翻译的文本
     * @param source     源语种
     * @param target     目标语种
     * @return JSONObject 翻译结果
     * @throws Exception
     */
    public static JSONObject translate(String sourceText, String source, String target) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(APPID);
        sb.append(sourceText);
        long salt = new Date().getTime();
        sb.append(salt);
        sb.append(APPKEY);
        // 计算并获取签名
        String sign = Md5DigestUtil.MD5(sb.toString(), "UTF-8").toLowerCase();

        // 调用百度翻译接口(https)
        String url = "https://fanyi-api.baidu.com/api/trans/vip/translate";
        StringBuilder sb1 = new StringBuilder();
        sb1.append(url);
        // 将待翻译的文本转换为UTF-8形式的URL编码
        String query = URLEncoder.encode(sourceText, "UTF-8");
        sb1.append("?q=" + query);
        sb1.append("&from=" + source + "&to=" + target);
        sb1.append("&appid=" + APPID);
        sb1.append("&salt=" + salt);
        sb1.append("&sign=" + sign);
        JSONObject jsonObject = JSONObjectUtil.doGetStr(sb1.toString());
        return jsonObject;
    }


}
