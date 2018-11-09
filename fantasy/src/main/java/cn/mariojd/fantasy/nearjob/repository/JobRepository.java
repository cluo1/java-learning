package cn.mariojd.fantasy.repository;

import cn.mariojd.fantasy.document.Job;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Jared
 * @date 2018/8/21 11:09
 */
public interface JobRepository extends ElasticsearchRepository<Job, String> {
}
