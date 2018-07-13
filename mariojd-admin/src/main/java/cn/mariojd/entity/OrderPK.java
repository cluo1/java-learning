package cn.mariojd.entity;

import java.io.Serializable;

/**
 * Created by Mario
 */
public class OrderPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sid;

    private Integer uid;

    public OrderPK() {
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPK)) return false;

        OrderPK orderPK = (OrderPK) o;

        if (!getSid().equals(orderPK.getSid())) return false;
        return getUid().equals(orderPK.getUid());

    }

    @Override
    public int hashCode() {
        int result = getSid().hashCode();
        result = 31 * result + getUid().hashCode();
        return result;
    }
}
