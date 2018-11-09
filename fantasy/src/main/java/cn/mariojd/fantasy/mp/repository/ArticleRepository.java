package cn.mariojd.fantasy.mp.repository;

import cn.mariojd.fantasy.mp.document.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Jared
 * @date 2018/11/9 15:28
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {

    Page<Article> findByMpsId(int mpsId, Pageable pageable);

    Page<Article> findByMpsIdOrTitleLikeOrAuthorLikeOrDigestLikeOrContentLike(
            int mpsId, String title, String author, String digest, String content, Pageable pageable);

}

