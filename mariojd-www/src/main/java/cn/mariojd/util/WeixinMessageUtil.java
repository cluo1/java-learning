package cn.mariojd.util;

import cn.mariojd.pojo.WeixinTextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信消息工具类
 *
 * @author Mario
 *
 */
public class WeixinMessageUtil {

    public static final String TEXT = "text";// 文本消息
    public static final String NEWS = "news";// 图文消息
    public static final String IMAGE = "image";// 图片消息
    public static final String LOCATION = "location";// 地理位置消息
    public static final String LINK = "link";// 链接消息

    public static final String EVENT = "event";// 推送事件
    public static final String SUBSCRIBE = "subscribe";// 关注事件
    public static final String UNSUBSCRIBE = "unsubscribe";// 取消关注事件
    public static final String CLICK = "CLICK";// 菜单点击事件
    public static final String VIEW = "VIEW";// 菜单查看事件
    public static final String SCANCODE_PUSH = "scancode_push";// 扫码事件
    public static final String LOCATION_SELECT = "location";// 地理位置事件

    /**
     * 关注公众号触发的方法
     *
     * @return String
     */
    public static String subscribe() {
        StringBuilder sb = new StringBuilder();
        sb.append("您好，欢迎。您可以\n");
        sb.append("输入【1】：获取关于作者 \n");
        sb.append("输入【?】：调出主菜单\n");
        sb.append("输入【-××】：中→英\n");
        sb.append("输入【/××】：中→粤语\n");
        sb.append("输入【*××】：中→繁体\n");
        sb.append("输入其它：我都可以回复你滴");
        return sb.toString();
    }

    /**
     * 将xml数据转换为map数据类型
     *
     * @param req
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> xmlToMap(HttpServletRequest req) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream is = req.getInputStream();
        Document doc = reader.read(is);
        Element root = doc.getRootElement();// 获取根元素
        List<Element> list = root.elements();
        for (Element element : list) {
            map.put(element.getName(), element.getText());
        }
        is.close();
        return map;
    }

    /**
     * 将文本消息组装成xml数据
     *
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return String
     */
    public static String initText(String toUserName, String fromUserName, String content) {
        WeixinTextMessage textMessage = new WeixinTextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(TEXT);
        textMessage.setContent(content);
        XStream xstream = new XStream();
        // 将xml的根节点由类的路径转换为<xml></xml>
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * 输入1获取关于作者
     *
     * @return String
     */
    public static String firstMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("您好，我是作者本人。\n");
//		sb.append("英文名叫Mario。目前就读于亚洲皇家学府，坐标广东茂名。我目前是一名大三的在校学生，暑假即将实习，目的地坐标暂定广州或深圳，更多敬请持续关注");
        return sb.toString();
    }

    /**
     * 输入？调出主菜单
     *
     * @return String
     */
    public static String menuText() {
        StringBuilder sb = new StringBuilder();
        sb.append("您好，欢迎。您可以\n");
        sb.append("输入【1】：获取关于作者 \n");
        sb.append("输入【?】：调出主菜单\n");
        sb.append("输入【-××】：中→英\n");
        sb.append("输入【/××】：中→粤语\n");
        sb.append("输入【*××】：中→繁体\n");
        sb.append("输入其它：我都可以回复你滴");
        return sb.toString();
    }
}
