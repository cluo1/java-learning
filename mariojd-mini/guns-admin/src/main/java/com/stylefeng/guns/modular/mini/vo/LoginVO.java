package com.stylefeng.guns.modular.mini.vo;

public class LoginVO {

    private String account;

    private String password;

    private Integer role;

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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginVO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
