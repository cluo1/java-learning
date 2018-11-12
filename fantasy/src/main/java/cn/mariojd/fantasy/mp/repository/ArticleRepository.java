package cn.mariojd.fantasy.mp.repository;

import cn.mariojd.fantasy.mp.document.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Jared
 * @date 2018/11/9 15:28
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {

    Article findByArticleId(int articleId);

}


