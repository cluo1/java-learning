package cn.mariojd.springboot.multiple.datasource.mybatis.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

/**
 * @author Jared
 * @date 2018/11/21 15:58
 */
@Profile("mybatis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boy {

    private Integer id;

    private String name;

}
