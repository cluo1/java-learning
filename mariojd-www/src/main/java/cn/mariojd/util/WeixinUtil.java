package cn.mariojd.util;

import cn.mariojd.pojo.*;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class WeixinUtil {

    // 测试号应用ID
    public static final String APPID = "wxff1c350745089bb6";
    // 测试号应用密钥
    public static final String APPSECRET = "fd8642f07f7fa1011d8ef77e672d7927";

    // token接口地址
    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 创建菜单的接口地址
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 查询菜单的接口地址
    public static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    // 删除菜单的接口地址
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    // 获取用户信息的接口地址
    public static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * get请求处理方法
     *
     * @param url
     * @return JSONObject
     */
    public static JSONObject doGetStr(String url) {
        JSONObject jsonObject = null;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        // HttpClient4 警告: Invalid cookie header 的解决方法
        HttpClientParams.setCookiePolicy(httpClient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return jsonObject;
    }

    /**
     * post请求处理方法
     *
     * @param url
     * @param outStr
     * @return JSONObject
     */
    public static JSONObject doPostStr(String url, String outStr) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // HttpClient4 警告: Invalid cookie header 的解决方法
        HttpClientParams.setCookiePolicy(httpClient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.fromObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * access_token
     *
     * @return access_token
     */
    public static WeixinAccessToken getAccessToken() {
        WeixinAccessToken token = new WeixinAccessToken();
        String url = TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            token.setAccess_token(jsonObject.getString("access_token"));
            token.setExpires_in(jsonObject.getInt("expires_in"));
        }
        return token;
    }

    /**
     * 组装按钮菜单
     *
     * @return menu
     */
    public static WeixinMenuButton initMenu() {
        // 菜单按钮1
        WeixinViewButton button1 = new WeixinViewButton();
        button1.setType("view");
        button1.setName("mariojd");
        button1.setUrl("https://www.mariojd.cn/");

        // 菜单按钮2
        WeixinViewButton button2 = new WeixinViewButton();
        button2.setType("view");
        button2.setName("Happy Mario");
        button2.setUrl("https://mariojd.cn/oauth");

        // 菜单按钮3-1
        WeixinClickButton button3_1 = new WeixinClickButton();
        button3_1.setType("scancode_push");
        button3_1.setName("扫二维码");
        button3_1.setKey("3_1");

        // 菜单按钮3-2
        WeixinClickButton button3_2 = new WeixinClickButton();
        button3_2.setType("location_select");
        button3_2.setName("地理位置");
        button3_2.setKey("3_2");

        // 菜单按钮3
        WeixinButton button3 = new WeixinButton();
        button3.setName("小工具");
        button3.setSub_button(new WeixinButton[] { button3_1, button3_2 });

        // 组装三个菜单
        WeixinMenuButton menu = new WeixinMenuButton();
        menu.setButton(new WeixinButton[] { button1, button2, button3 });
        return menu;
    }

    /**
     * 创建菜单的方法
     *
     * @param token
     * @param menu
     * @return int
     */
    public static int createMenu(String token, String menu) {
        Integer result = null;
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doPostStr(url, menu);
        if (jsonObject != null) {
            result = jsonObject.getInt("errcode");
        }
        return result;
    }

    /**
     * 查询菜单
     *
     * @param token
     * @return JSONObject
     */
    public static JSONObject queryMenu(String token) {
        String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doGetStr(url);
        return jsonObject;
    }

    /**
     * 删除菜单
     *
     * @param token
     * @return int
     */
    public static int deleteMenu(String token) {
        Integer result = null;
        String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doGetStr(url);
        if (jsonObject != null) {
            result = jsonObject.getInt("errcode");
        }
        return result;
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @param openid
     * @return JSONObject
     */
    public static JSONObject getUserInfo(String token, String openid) {
        String url = USER_INFO.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
        System.out.println(url);
        JSONObject jsonObject = doGetStr(url);
        return jsonObject;
    }


}
