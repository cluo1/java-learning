package cn.mariojd.dto;

/**
 * Created by Mario
 * 抢购地址暴露信息的dto对象
 */
public class SeckillExposure {

    private boolean exposed;//抢购是否开始

    private String md5;//加密措施

    private Integer sid;

    private long now;//系统当前时间(毫秒)

    private long start;//抢购开始时间

    private long end;//抢购结束时间


    public SeckillExposure(boolean exposed, Integer sid) {
        this.exposed = exposed;
        this.sid = sid;
    }

    public SeckillExposure(boolean exposed, String md5, Integer sid) {
        this.exposed = exposed;
        this.md5 = md5;
        this.sid = sid;
    }

    public SeckillExposure(boolean exposed, Integer sid, long now, long start, long end) {
        this.exposed = exposed;
        this.sid = sid;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "SeckillExposure{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", sid=" + sid +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}

