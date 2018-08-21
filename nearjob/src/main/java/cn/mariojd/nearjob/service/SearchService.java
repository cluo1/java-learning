package cn.mariojd.nearjob.service;

import cn.mariojd.nearjob.document.Job;
import cn.mariojd.nearjob.model.request.SearchVO;
import cn.mariojd.nearjob.model.response.SearchResultVO;
import cn.mariojd.nearjob.repository.SearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.util.Objects;

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
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();

        Integer cityId = searchVO.getCityId();
        if (Objects.nonNull(cityId)) {
            queryBuilder.must(new MatchQueryBuilder("city_id", cityId));
        }

        Integer sourceFrom = searchVO.getSourceFrom();
        if (Objects.nonNull(sourceFrom)) {
            queryBuilder.must(new MatchQueryBuilder("source_from", sourceFrom));
        }

        String keyword = searchVO.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            queryBuilder.must(new MatchQueryBuilder("keyword", keyword));
        }

        Float latitude = searchVO.getLatitude();
        Float longitude = searchVO.getLongitude();
        if (Objects.nonNull(latitude) && Objects.nonNull(longitude)) {
            Integer distance = searchVO.getDistance();
            GeoDistanceQueryBuilder builder = new GeoDistanceQueryBuilder("location");
            builder.distance(String.valueOf(distance));
            builder.point(latitude, longitude);
            queryBuilder.filter(builder);
        }


        Page<Job> search = searchRepository.search(queryBuilder, pageable);
        return null;
    }

    private Job toSearchResultVO(Job document) {
        SearchResultVO resultVO = new SearchResultVO();
        BeanUtils.copyProperties(document, resultVO);
        return null;
    }

}
