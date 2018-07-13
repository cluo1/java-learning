package cn.mariojd.mini.program.vo.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class AuthorizeVO {

    @NotBlank(message = "密文不能为空")
    private String data;

    @NotBlank(message = "iv不能为空")
    private String iv;

    @NotBlank(message = "code不能为空")
    private String code;

}
