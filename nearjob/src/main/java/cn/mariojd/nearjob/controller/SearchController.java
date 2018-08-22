package cn.mariojd.nearjob.controller;

import cn.mariojd.nearjob.model.request.SearchVO;
import cn.mariojd.nearjob.model.response.SearchResultVO;
import cn.mariojd.nearjob.service.SearchService;
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
 * 搜索模块
 *
 * @author Jared
 * @date 2018/8/21 9:20
 */
@RestController
@RequestMapping("api/search")
@Slf4j
public class SearchController {

    @Resource
    private SearchService searchService;

    @GetMapping
    public Page<SearchResultVO> findPage(@ModelAttribute @Valid SearchVO searchVO,
                                         Pageable pageable) {
        return searchService.findPage(searchVO, pageable);
    }

}
