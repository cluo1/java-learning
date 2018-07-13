package cn.mariojd.pojo;

public class WeixinButton {

    private String type;
    private String name;
    private WeixinButton[] sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeixinButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(WeixinButton[] sub_button) {
        this.sub_button = sub_button;
    }

}
