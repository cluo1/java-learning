package cn.mariojd.fantasy.mp.service;

import cn.mariojd.fantasy.mp.document.Article;
import cn.mariojd.fantasy.mp.model.request.ArticleSearchVO;
import cn.mariojd.fantasy.mp.model.response.ArticleResultVO;
import cn.mariojd.fantasy.mp.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2018/11/9 15:24
 */
@Slf4j
@Service
public class ArticleService {

    @Resource
    private ArticleRepository articleRepository;

    /**
     * 根据查询条件搜索
     *
     * @param searchVO
     * @param pageable
     * @return
     */
    public Page<ArticleResultVO> findPage(ArticleSearchVO searchVO, Pageable pageable) {
        int mpsId = searchVO.getMpsId();
        String word = searchVO.getKeyword();
        if (StringUtils.isEmpty(word)) {
            return articleRepository.findByMpsId(mpsId, pageable).map(this::toArticleResultVO);
        }
        return articleRepository.findByMpsIdOrTitleLikeOrAuthorLikeOrDigestLikeOrContentLike(
                mpsId, word, word, word, word, pageable).map(this::toArticleResultVO);
    }

    /**
     * Article -> ArticleResultVO
     *
     * @param article
     * @return ArticleResultVO
     */
    private ArticleResultVO toArticleResultVO(Article article) {
        ArticleResultVO resultVO = new ArticleResultVO();
        BeanUtils.copyProperties(article, resultVO);
        return resultVO;
    }

}
