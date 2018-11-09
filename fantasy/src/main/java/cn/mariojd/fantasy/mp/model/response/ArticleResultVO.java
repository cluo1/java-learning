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
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String author;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 短语描述
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

}
