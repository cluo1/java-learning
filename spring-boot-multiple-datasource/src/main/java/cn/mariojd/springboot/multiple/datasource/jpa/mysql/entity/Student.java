package cn.mariojd.springboot.multiple.datasource.jpa.mysql.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Jared
 * @date 2018/11/21 19:45
 * @blog: https://blog.mariojd.cn/
 */
@Data
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

}
