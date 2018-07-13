package cn.mariojd.mini.program.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizeResultVO {

    /**
     * 认证票据
     */
    private String token;

}
