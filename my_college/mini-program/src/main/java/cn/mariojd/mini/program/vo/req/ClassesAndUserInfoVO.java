package cn.mariojd.mini.program.vo.req;

import lombok.Data;

import java.util.List;

@Data
public class ClassesAndUserInfoVO {

    /**
     * 班级id，老师可能有多个
     */
    private List<Integer> ids;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 班级id，学生只有一个
     */
    private Integer id;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 性别
     */
    private Integer gender;

}
