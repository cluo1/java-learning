package cn.mariojd.mini.program.vo.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class StudentClassesVO {

    /**
     * 班级id，学生只有一个
     */
    @NotEmpty(message = "请选择班级信息")
    private Integer id;

    /**
     * 真实姓名
     */
    @NotBlank(message = "请输入真实姓名")
    private String realName;

    /**
     * 学号
     */
    @NotBlank(message = "请输入学号")
    private String studentId;

    /**
     * 性别
     */
    @NotBlank(message = "请输入性别")
    private Integer gender;

}
