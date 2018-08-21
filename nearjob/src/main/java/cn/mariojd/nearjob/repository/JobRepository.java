package cn.mariojd.nearjob.repository;

import cn.mariojd.nearjob.document.Job;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Jared
 * @date 2018/8/21 11:09
 */
public interface JobRepository extends ElasticsearchRepository<Job, String> {
}
