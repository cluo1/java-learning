package cn.mariojd.mini.program.vo.req;

import cn.mariojd.mini.program.enums.RoleEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProvAndSchoolAndRoleVO {

    /**
     * 省份id
     */
    @NotNull(message = "请选择所在省份")
    private Integer pid;

    /**
     * 学校id
     */
    @NotNull(message = "请选择所在学校")
    private Integer sid;

    /**
     * 身份
     */
    @NotNull(message = "请选择你的身份")
    private RoleEnum roleEnum;

}
