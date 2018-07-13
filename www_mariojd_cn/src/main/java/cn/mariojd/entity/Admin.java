package cn.mariojd.entity;

import java.io.Serializable;

/**
 * Created by Mario
 */
public class Admin implements Serializable{

    private static final long serialVersionUID = 1L;
    private Integer aid;
    private String nickname;
    private String account;
    private String password;
    private String role;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", nickname='" + nickname + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
