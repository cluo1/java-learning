package cn.mariojd.nearjob.base;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * @author Jared
 * @date 2018/8/21 10:27
 */
@Data
public class BaseDocument {

    /**
     * 注解id
     */
    @Id
    private Integer id;

    /**
     * 城市id
     */
    @Field(type = FieldType.Integer)
    private Integer cityId;

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
