package cn.mariojd.mini.program.vo.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
public class TeacherClassesVO {

    /**
     * 班级id，老师可能有多个
     */
    @NotEmpty(message = "请选择班级信息")
    private List<Integer> ids;

    /**
     * 真实姓名
     */
    @NotBlank(message = "请输入真实姓名")
    private String realName;

    /**
     * 性别
     */
    @NotBlank(message = "请输入性别")
    private Integer gender;

}
