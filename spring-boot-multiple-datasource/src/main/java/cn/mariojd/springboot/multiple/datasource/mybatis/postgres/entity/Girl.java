package cn.mariojd.springboot.multiple.datasource.mybatis.postgres.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;

/**
 * @author Jared
 * @date 2018/11/21 16:27
 */
@Profile("mybatis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Girl {

    private Integer id;

    private String name;

}
