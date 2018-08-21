package cn.mariojd.nearjob.controller;

import cn.mariojd.nearjob.model.request.SearchVO;
import cn.mariojd.nearjob.model.response.SearchResultVO;
import cn.mariojd.nearjob.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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
    public Flux<SearchResultVO> findBySearchVO(@ModelAttribute @Valid SearchVO searchVO,
                                               @PageableDefault(size = 20) Pageable pageable) {
        return searchService.findBySearchVO(searchVO, pageable);
    }

}
