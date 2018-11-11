package cn.mariojd.fantasy.mp.model.response;

import lombok.Data;

import java.util.Date;

/**
 * @author Jared
 * @date 2018/11/9 15:27
 */
@Data
public class ArticleResultVO {

    /**
     * 主键Id
     */
    private Integer articleId;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 封面图 or 图片消息
     */
    private String cover;

    /**
     * 短语描述  or 文字消息
     */
    private String digest;

    /**
     * 原文地址
     */
    private String contentURL;

    /**
     * 发布时间
     */
    private Date postTime;

    /**
     * 文章类型。1：文字；3：图片；49：图文
     */
    private Integer msgType;

}
