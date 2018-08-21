package cn.mariojd.nearjob.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * @author Jared
 * @date 2018/8/21 10:27
 */
@Data
@Document(indexName = "nearjob", type = "job")
public class Job {

    /**
     * 主键id(同下)
     */
    @Id
    private String id;

    /**
     * 标志id(同上)
     */
    @Field(type = FieldType.Keyword)
    private String positionId;

    /**
     * 城市id
     */
    @Field(type = FieldType.Integer)
    private Integer cityId;

    /**
     * 岗位id
     */
    @Field(type = FieldType.Integer)
    private Integer jobId;

    /**
     * 坐标位置
     */
    @GeoPointField
    private GeoPoint location;

    /**
     * 数据来源
     */
    @Field(type = FieldType.Integer)
    private Integer sourceFrom;

    /**
     * 关键字
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String keyword;

}
