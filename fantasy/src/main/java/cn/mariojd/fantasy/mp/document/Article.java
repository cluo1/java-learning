package cn.mariojd.fantasy.mp.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author Jared
 * @date 2018/11/9 15:12
 */
@Data
@Document(indexName = "mp", type = "article")
public class Article {

    /**
     * 自增主键
     */
    @Field(type = FieldType.Integer)
    private Integer articleId;

    /**
     * 消息id (唯一)
     */
    @Id
    private Integer msgId;

    /**
     * 公众号外键id
     */
    @Field(type = FieldType.Integer)
    private Integer mpsId;

    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    /**
     * 作者
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String author;

    /**
     * 封面图
     */
    @Field(type = FieldType.Keyword)
    private String cover;

    /**
     * 短语描述
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String digest;

    /**
     * 文章内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;

    /**
     * 原文地址
     */
    @Field(type = FieldType.Keyword)
    private String contentURL;

    /**
     * 文章地址
     */
    @Field(type = FieldType.Keyword)
    private String sourceURL;

    /**
     * 发布时间
     */
    @Field(type = FieldType.Date)
    private Date postTime;

}
