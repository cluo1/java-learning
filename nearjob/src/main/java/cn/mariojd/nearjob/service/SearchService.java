package cn.mariojd.nearjob.service;

import cn.mariojd.nearjob.model.request.SearchVO;
import cn.mariojd.nearjob.model.response.SearchResultVO;
import cn.mariojd.nearjob.repository.SearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2018/8/21 10:19
 */
@Slf4j
@Service
public class SearchService {

    @Resource
    private SearchRepository searchRepository;

    public Flux<SearchResultVO> findBySearchVO(SearchVO searchVO, Pageable pageable) {
        QueryBuilder queryBuilder = new GeoDistanceQueryBuilder("");
        searchRepository.search(queryBuilder, pageable);
        return null;
    }

}
