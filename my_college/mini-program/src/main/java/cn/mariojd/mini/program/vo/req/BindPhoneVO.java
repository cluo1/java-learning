package cn.mariojd.mini.program.vo.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class BindPhoneVO {

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotBlank(message = "验证码不能为空")
    private String code;

}
