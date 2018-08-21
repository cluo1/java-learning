package cn.mariojd.nearjob.repository;

import cn.mariojd.nearjob.base.BaseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Jared
 * @date 2018/8/21 11:09
 */
public interface SearchRepository extends ElasticsearchRepository<BaseDocument, Integer> {
}
