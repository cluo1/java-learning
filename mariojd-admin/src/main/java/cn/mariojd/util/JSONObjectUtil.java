package cn.mariojd.util;

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


public class JSONObjectUtil {

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

}
