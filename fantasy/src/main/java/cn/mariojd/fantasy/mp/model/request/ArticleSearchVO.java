package cn.mariojd.fantasy.mp.model.request;

import lombok.Data;

import java.util.Date;

/**
 * @author Jared
 * @date 2018/11/9 15:26
 */
@Data
public class ArticleSearchVO {

    /**
     * 公众号Id
     */
    private Integer mpsId;

    /**
     * 搜索关键字
     */
    private String keyword;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 文章类型。1：文字；3：图片；49：图文
     */
    private Integer msgType;

}
