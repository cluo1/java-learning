package cn.mariojd.springboot.multiple.datasource.jpa.postgres.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Jared
 * @date 2018/11/21 19:47
 * @blog: https://blog.mariojd.cn/
 */
@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
