package cn.mariojd.nearjob.service;

import cn.mariojd.nearjob.base.BaseEntity;
import cn.mariojd.nearjob.base.BaseService;
import cn.mariojd.nearjob.document.Job;
import cn.mariojd.nearjob.entity.Java;
import cn.mariojd.nearjob.model.request.SearchVO;
import cn.mariojd.nearjob.model.response.IndexResultVO;
import cn.mariojd.nearjob.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author Jared
 * @date 2018/8/21 10:19
 */
@Slf4j
@Service
public class IndexService extends BaseService {

    @Resource
    private JobRepository jobRepository;

    /**
     * 获取首页展示数据
     *
     * @param searchVO
     * @param pageable
     * @return
     */
    public Page<IndexResultVO> findPage(SearchVO searchVO, Pageable pageable) {
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        int jobId = searchVO.getJobId();
        queryBuilder.must(new MatchQueryBuilder("jobId", jobId));

        Integer cityId = searchVO.getCityId();
        if (Objects.nonNull(cityId)) {
            queryBuilder.must(new MatchQueryBuilder("cityId", cityId));
        }

        Integer sourceFrom = searchVO.getSourceFrom();
        if (Objects.nonNull(sourceFrom)) {
            queryBuilder.must(new MatchQueryBuilder("sourceFrom", sourceFrom));
        }

        String keyword = searchVO.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            queryBuilder.must(new MatchQueryBuilder("keyword", keyword));
        }

        Double latitude = searchVO.getLatitude();
        Double longitude = searchVO.getLongitude();
        if (Objects.nonNull(latitude) && Objects.nonNull(longitude)) {
            Integer distance = searchVO.getDistance();
            GeoDistanceQueryBuilder builder = new GeoDistanceQueryBuilder("location");
            builder.distance(String.valueOf(distance), DistanceUnit.KILOMETERS);
            builder.point(latitude, longitude);
            queryBuilder.filter(builder);
        }

        return jobRepository.search(queryBuilder, pageable).map(this::toSearchResultVO);
    }

    /**
     * Job -> IndexResultVO
     *
     * @param job
     * @return IndexResultVO
     */
    private IndexResultVO toSearchResultVO(Job job) {
        IndexResultVO resultVO = new IndexResultVO();
        Java java = javaDao.findByPositionId(job.getPositionId());
        BeanUtils.copyProperties(java, resultVO);
        return resultVO;
    }

}