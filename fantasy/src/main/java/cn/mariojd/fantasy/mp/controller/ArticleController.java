package cn.mariojd.fantasy.mp.controller;

import cn.mariojd.fantasy.mp.model.request.ArticleSearchVO;
import cn.mariojd.fantasy.mp.model.response.ArticleResultVO;
import cn.mariojd.fantasy.mp.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author Jared
 * @date 2018/11/9 15:24
 */
@RestController
@RequestMapping("api/article")
@Slf4j
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping
    public Page<ArticleResultVO> findPage(@ModelAttribute @Valid ArticleSearchVO searchVO,
                                          @PageableDefault(size = 40, sort = "postTime", direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.findPage(searchVO, pageable);
    }

}
