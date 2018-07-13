package com.stylefeng.guns.modular.mini.vo;

import java.util.List;

public class LoginSuccessVO<T> {

    private List<T> items;

    private Integer uid;

    private Integer sysRole;

    public Integer getSysRole() {
        return sysRole;
    }

    public void setSysRole(Integer sysRole) {
        this.sysRole = sysRole;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
