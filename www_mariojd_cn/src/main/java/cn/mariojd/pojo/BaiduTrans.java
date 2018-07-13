package cn.mariojd.pojo;

public class BaiduTrans {

    // 翻译源语言
    private String from;
    // 译文语言
    private String to;
    // 翻译结果
    private BaiduTransResult[] trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BaiduTransResult[] getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(BaiduTransResult[] trans_result) {
        this.trans_result = trans_result;
    }

}
