package cn.mariojd.dto;

import cn.mariojd.enums.SeckillEnum;

/**
 * Created by Mario
 * 封装抢购结果的dto对象
 */
public class SeckillExecution {

    private Integer sid;

    //抢购结果状态
    private int state;

    //抢购结果信息
    private String stateInfo;

    public SeckillExecution(Integer sid, SeckillEnum seckillEnum) {
        this.sid = sid;
        this.state = seckillEnum.getState();
        this.stateInfo = seckillEnum.getStateInfo();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "sid=" + sid +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                '}';
    }
}
