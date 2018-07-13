package cn.mariojd.pojo;

public class WeixinTextMessage extends WeixinBaseMessage {

    private String Content;// 文本消息内容
    private Integer MsgId;// 消息id，64位整型

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Integer getMsgId() {
        return MsgId;
    }

    public void setMsgId(Integer msgId) {
        MsgId = msgId;
    }

}
