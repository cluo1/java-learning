package com.example.demo.entity;

import lombok.Data;

/**
 * @author jdq
 * @date 2018/4/2 19:06
 */
@Data
public class User {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 年龄
     */
    private Integer age;

}
