package cn.mariojd.enums;

/**
 * Created by Mario
 * 使用枚举表示抢购相关的处理结果
 */
public enum SeckillEnum {

    SECKILL_SUCCESS(1, "抢购成功"),
    SECKILL_END(0, "活动结束"),
    SECKILL_REPEAT_KILL(-1, "重复抢购"),
    SECKILL_INNERE_RROR(-2, "系统异常"),
    SECKILL_DATA_REWRITE(-3, "数据篡改");

    private int state;

    private String stateInfo;

    SeckillEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    /**
     * 根据传入的state返回对应的SeckillEnum
     * 此处的state应跟存储过程返回的result相对应
     * @param stateCode
     * @return SeckillEnum
     */
    public static SeckillEnum stateOf(int stateCode) {
        for (SeckillEnum state : SeckillEnum.values()) {
            if (state.getState() == stateCode) {
                return state;
            }
        }
        return null;
    }

}
