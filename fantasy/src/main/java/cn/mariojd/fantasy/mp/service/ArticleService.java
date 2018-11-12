package cn.mariojd.fantasy.mp.service;

import cn.mariojd.fantasy.mp.document.Article;
import cn.mariojd.fantasy.mp.model.request.ArticleSearchVO;
import cn.mariojd.fantasy.mp.model.response.ArticleResultVO;
import cn.mariojd.fantasy.mp.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

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
        Date startTime = searchVO.getStartTime();
        Date endTime = searchVO.getEndTime();
        Integer msgType = searchVO.getMsgType();

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withPageable(pageable).withFilter(new MatchQueryBuilder("mpsId", mpsId));

        if (!Objects.isNull(msgType)) {
            builder.withFilter(new MatchQueryBuilder("msgType", msgType));
        }
        if (!Objects.isNull(startTime) && !Objects.isNull(endTime)) {
            builder.withFilter(new RangeQueryBuilder("postTime").gte(startTime).lte(endTime));
        }
        if (!StringUtils.isEmpty(word)) {
            MultiMatchQueryBuilder multiMatchQueryBuilder = new MultiMatchQueryBuilder(word, "title", "digest", "content", "author")
                    .field("content", 2.0f).field("title", 3.0f);
            builder.withQuery(multiMatchQueryBuilder);
        }

        return articleRepository.search(builder.build()).map(this::toArticleResultVO);
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

    /**
     * 根据ArticleId获取contentURL
     *
     * @param articleId
     * @return
     */
    public String getContentURL(int articleId) {
        return "";
    }

}
