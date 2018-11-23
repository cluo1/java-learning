package cn.mariojd.fantasy.mp.controller;

import cn.mariojd.fantasy.mp.model.response.WeChatResultVO;
import cn.mariojd.fantasy.mp.service.WeChatMpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2018/11/23 9:45
 */
@RestController
@RequestMapping("api/mp")
@Slf4j
public class WeChatMpController {

    @Resource
    private WeChatMpService weChatMpService;

    @GetMapping("list")
    public Page<WeChatResultVO> findPage(@PageableDefault(size = 20) Pageable pageable) {
        return weChatMpService.findPage(pageable);
    }

}
