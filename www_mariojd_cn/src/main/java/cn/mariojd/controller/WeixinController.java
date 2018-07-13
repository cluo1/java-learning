package cn.mariojd.controller;

import cn.mariojd.base.BaseController;
import cn.mariojd.entity.User;
import cn.mariojd.pojo.BaiduTrans;
import cn.mariojd.pojo.BaiduTransResult;
import cn.mariojd.util.BaiduUtil;
import cn.mariojd.util.WeixinMessageUtil;
import cn.mariojd.util.WeixinTokenUtil;
import cn.mariojd.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

@Controller
public class WeixinController extends BaseController {

    @GetMapping("/wx")
    public void checkSignature(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // signature : 微信加密签名，结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
        String signature = req.getParameter("signature");
        // 时间戳
        String timestamp = req.getParameter("timestamp");
        // 随机数
        String nonce = req.getParameter("nonce");
        // 随机字符串
        String echostr = req.getParameter("echostr");
        PrintWriter out = resp.getWriter();
        if (WeixinTokenUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
    }

    @PostMapping("/wx")
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        try {
            Map<String, String> map = WeixinMessageUtil.xmlToMap(req);// 通过map集合接收消息
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String message = null;

            // 1、触发文本消息事件
            if (WeixinMessageUtil.TEXT.equals(msgType)) {
                if ("1".equals(content.trim())) {
                    // 输入1
                    message = WeixinMessageUtil.initText(toUserName, fromUserName, WeixinMessageUtil.firstMenu());
                } else if ("?".equals(content.trim()) || "？".equals(content.trim())) {
                    // 输入？
                    message = WeixinMessageUtil.initText(toUserName, fromUserName, WeixinMessageUtil.menuText());
                } else if (content.trim().matches("[a-zA-Z]+")) {
                    String soucre = "en";
                    String target = "zh";
                    JSONObject jsonObject = BaiduUtil.translate(content, soucre, target);
                    Object obj = jsonObject.get("trans_result");
                    // 译文
                    String dst = null;
                    // 拼接多译文
                    StringBuilder dst1 = new StringBuilder();
                    if (!"[]".equals(obj.toString())) {
                        BaiduTrans trans = (BaiduTrans) JSONObject.toBean(jsonObject, BaiduTrans.class);
                        BaiduTransResult[] transResult = trans.getTrans_result();
                        for (BaiduTransResult tr : transResult) {
                            dst = tr.getDst();
                            dst1.append(dst + " ");
                        }
                    }
                    message = WeixinMessageUtil.initText(toUserName, fromUserName, dst1.toString());
                } else {
                    // 其它文本输入直接返回输入内容
                    message = WeixinMessageUtil.initText(toUserName, fromUserName, "你说：" + content);
                }
            }

            // 2、触发推送事件
            if (WeixinMessageUtil.EVENT.equals(msgType)) {
                String eventType = map.get("Event");
                if (WeixinMessageUtil.SUBSCRIBE.equals(eventType)) {
                    // 关注
                    message = WeixinMessageUtil.initText(toUserName, fromUserName, WeixinMessageUtil.subscribe());
                } else if (WeixinMessageUtil.VIEW.equals(eventType)) {
                    // 点击查看按钮
                    String eventKey = map.get("EventKey");
                    message = WeixinMessageUtil.initText(toUserName, fromUserName, eventKey);
                } else if (WeixinMessageUtil.SCANCODE_PUSH.equals(eventType)) {
                    // 扫码
                    String eventKey = map.get("EventKey");
                    System.out.println(eventKey);
                    message = WeixinMessageUtil.initText(toUserName, fromUserName, eventKey);
                }
            }

            // 3、触发地理位置事件
            if (WeixinMessageUtil.LOCATION_SELECT.equals(msgType)) {
                String label = map.get("Label");
                message = WeixinMessageUtil.initText(toUserName, fromUserName, "你的当前位置是：" + label);
            }
            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @GetMapping("/oauth")
    public String oauth() {
        return "user/oauth";
    }

    @GetMapping("/oauth/login")
    public String login() throws IOException {
        String redirectURL = "https://www.mariojd.cn/oauth/callback";
        String getCodeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeixinUtil.APPID
                + "&redirect_uri=" + URLEncoder.encode(redirectURL, "UTF-8") + "&response_type=code"
                + "&scope=snsapi_userinfo" + "&state=STATE#wechat_redirect";
        return "redirect:" + getCodeUrl;
    }

    @RequestMapping("/oauth/callback")
    public String callback(HttpServletRequest req) {
        String code = req.getParameter("code");
        String getAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeixinUtil.APPID
                + "&secret=" + WeixinUtil.APPSECRET + "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = WeixinUtil.doGetStr(getAccessTokenUrl);
        String openid = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");
        String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid="
                + openid + "&lang=zh_CN";
        JSONObject weixinUser = WeixinUtil.doGetStr(getUserInfoUrl);
        String nickname = weixinUser.getString("nickname");
        String icon = weixinUser.getString("headimgurl");
        User user = userService.getUserByOpenid(openid);
        if (user == null) {
            userService.saveWeixinUser(openid, nickname, icon);
        } else if (!user.getNickname().equals(nickname) || !user.getIcon().equals(icon)) {
            userService.updateWeixinUserByOpenid(openid, nickname, icon);
        }
        user.setNickname(nickname);
        user.setIcon(icon);
        user.setOpenid(openid);
        req.getSession().setAttribute("user", user);
        return "redirect:/";
    }

}
