package cn.mariojd.entity;

import java.io.Serializable;

/**
 * Created by Mario
 */
public class User implements Serializable {

    private static final long serialVersionUID = 7247714666080613254L;
    private Integer uid;
    private String nickname;
    private String telephone;
    private String email;
    private String salt;
    private String password;
    private String icon;
    private Integer count;
    private Integer state;
    private String code;
    private String openid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", nickname='" + nickname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", icon='" + icon + '\'' +
                ", count=" + count +
                ", state=" + state +
                ", code='" + code + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
