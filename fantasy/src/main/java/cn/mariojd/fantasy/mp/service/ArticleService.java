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
import org.springframework.cache.annotation.CacheConfig;
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
@CacheConfig(cacheNames = "ArticleService")
public class ArticleService {

    @Value("${fantasy.mp-index}")
    private String index;

    @Resource
    private ArticleRepository articleRepository;

    /**
     * 根据查询条件搜索
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
            String epoch = String.valueOf(startTime).length() == 10 ? "epoch_second" : "epoch_millis";
            boolQueryBuilder.must(QueryBuilders.rangeQuery("postTime").format(epoch).gte(startTime).lte(endTime));
        }
        SortBuilder sortBuilder = SortBuilders.fieldSort("postTime").order(SortOrder.DESC);
        if (!StringUtils.isEmpty(word)) {
            MultiMatchQueryBuilder multiMatchQueryBuilder =
                    new MultiMatchQueryBuilder(word, "title", "content", "digest", "author")
                            .field("title", 100).field("content", 50)
                            .field("digest", 30);
            // FunctionScoreQueryBuilder scoreQueryBuilder = QueryBuilders
            //        .functionScoreQuery(QueryBuilders.multiMatchQuery(multiMatchQueryBuilder));
            // builder.withQuery(scoreQueryBuilder);
            boolQueryBuilder.filter(multiMatchQueryBuilder);
            sortBuilder = SortBuilders.scoreSort().order(SortOrder.DESC);
        }

        builder.withQuery(boolQueryBuilder).withPageable(pageable).withSort(sortBuilder);
        return articleRepository.search(builder.build()).map(this::toArticleResultVO);
    }

    /**
     * Article -> ArticleResultVO
     */
    private ArticleResultVO toArticleResultVO(Article article) {
        ArticleResultVO resultVO = new ArticleResultVO();
        BeanUtils.copyProperties(article, resultVO);
        return resultVO;
    }

    /**
     * 根据ArticleId获取contentURL
     */
    @Cacheable(key = "#root.targetClass.simpleName+'.'+#root.methodName+'.'+#articleId")
    public String getContentURL(int articleId) {
        log.info("获取详情Article Detail:{}", articleId);
        Article article = articleRepository.findByArticleId(articleId);
        if (Objects.nonNull(article)) {
            String contentURL = article.getContentURL();
            if (!StringUtils.isEmpty(contentURL)) {
                return contentURL;
            }
        }
        return index;
    }

}
