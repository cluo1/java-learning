package cn.mariojd.fantasy.nearjob.controller;

import cn.mariojd.fantasy.nearjob.model.request.SearchVO;
import cn.mariojd.fantasy.nearjob.model.response.IndexResultVO;
import cn.mariojd.fantasy.nearjob.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 首页模块
 *
 * @author Jared
 * @date 2018/8/21 9:20
 */
@RestController
@RequestMapping("api/index")
@Slf4j
public class IndexController {

    @Resource
    private IndexService indexService;

    @GetMapping
    public Page<IndexResultVO> findPage(@ModelAttribute @Valid SearchVO searchVO, Pageable pageable) {
        return indexService.findPage(searchVO, pageable);
    }

}
