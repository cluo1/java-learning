package cn.mariojd.fantasy.mp.service;

import cn.mariojd.fantasy.mp.document.Article;
import cn.mariojd.fantasy.mp.model.request.ArticleSearchVO;
import cn.mariojd.fantasy.mp.model.response.ArticleResultVO;
import cn.mariojd.fantasy.mp.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Jared
 * @date 2018/11/9 15:24
 */
@Slf4j
@Service
public class ArticleService {

    @Value("${fantasy.mp-index}")
    private String index;

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
        Long startTime = searchVO.getStartTime();
        Long endTime = searchVO.getEndTime();
        Integer msgType = searchVO.getMsgType();
        log.info("查询搜索mpsId:{} ;word:{} ;msgType:{} ;startTime:{} ;endTime:{}", mpsId, word, msgType, startTime, endTime);

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("mpsId", mpsId));

        if (Objects.nonNull(msgType)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("msgType", msgType));
        }
        if (Objects.nonNull(startTime) && Objects.nonNull(endTime)) {
            String format = String.valueOf(startTime).length() == 10 ? "epoch_second" : "epoch_millis";
            boolQueryBuilder.must(QueryBuilders.rangeQuery("postTime").format(format).gte(startTime).lte(endTime));
        }
        SortBuilder sortBuilder;
        if (!StringUtils.isEmpty(word)) {
            MultiMatchQueryBuilder multiMatchQueryBuilder = new MultiMatchQueryBuilder(word, "title", "content", "digest", "author")
                    .field("title", 50).field("content", 20).field("digest", 15);
            boolQueryBuilder.filter(multiMatchQueryBuilder);
            sortBuilder = SortBuilders.scoreSort().order(SortOrder.DESC);
        } else {
            sortBuilder = SortBuilders.fieldSort("postTime").order(SortOrder.DESC);
        }
        log.info(sortBuilder.toString().replaceAll("\n", ""));

        builder.withQuery(boolQueryBuilder).withPageable(pageable).withSort(sortBuilder);
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
    @Cacheable(cacheNames = "ArticleService-getContentURL")
    public String getContentURL(int articleId) {
        log.info("获取详情Article Detail:{}", articleId);
        Article article = articleRepository.findByArticleId(articleId);
        if (Objects.nonNull(article)) {
            return article.getContentURL();
        }
        return index;
    }

}
