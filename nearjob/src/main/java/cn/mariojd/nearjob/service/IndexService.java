package cn.mariojd.nearjob.service;

import cn.mariojd.nearjob.base.BaseDao;
import cn.mariojd.nearjob.base.BaseEntity;
import cn.mariojd.nearjob.base.BaseService;
import cn.mariojd.nearjob.document.Job;
import cn.mariojd.nearjob.model.request.SearchVO;
import cn.mariojd.nearjob.model.response.IndexResultVO;
import cn.mariojd.nearjob.repository.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
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
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        int jobId = searchVO.getJobId();
        boolQueryBuilder.must(new MatchQueryBuilder("jobId", jobId));

        Integer cityId = searchVO.getCityId();
        if (Objects.nonNull(cityId)) {
            boolQueryBuilder.must(new MatchQueryBuilder("cityId", cityId));
        }

        Integer sourceFrom = searchVO.getSourceFrom();
        if (Objects.nonNull(sourceFrom)) {
            boolQueryBuilder.must(new MatchQueryBuilder("sourceFrom", sourceFrom));
        }

        String keyword = searchVO.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            boolQueryBuilder.must(new MatchQueryBuilder("keyword", keyword));
        }

        Double latitude = searchVO.getLatitude();
        Double longitude = searchVO.getLongitude();
        if (Objects.nonNull(latitude) && Objects.nonNull(longitude)) {
            // 指定距离范围
            int distance = searchVO.getDistance();
            GeoDistanceQueryBuilder distanceQueryBuilder = new GeoDistanceQueryBuilder("location");
            distanceQueryBuilder.point(latitude, longitude);
            distanceQueryBuilder.distance(String.valueOf(distance), DistanceUnit.KILOMETERS);
            boolQueryBuilder.filter(distanceQueryBuilder);

        }
        // 按照距离排序
        GeoDistanceSortBuilder distanceSortBuilder =
                new GeoDistanceSortBuilder("location", latitude, longitude);
        distanceSortBuilder.unit(DistanceUnit.KILOMETERS);
        distanceSortBuilder.order(SortOrder.ASC);
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder)
                .withSort(distanceSortBuilder).withPageable(pageable).build();

        return jobRepository.search(query).map(source ->
                toSearchResultVO(source, latitude, longitude, resources.get(jobId)));
    }

    /**
     * Job -> IndexResultVO
     *
     * @param job
     * @return IndexResultVO
     */
    private IndexResultVO toSearchResultVO(Job job, Double latitude, Double longitude, BaseDao baseDao) {
        IndexResultVO resultVO = new IndexResultVO();
        BaseEntity baseEntity = baseDao.findPartByPositionId(job.getPositionId());
        BeanUtils.copyProperties(baseEntity, resultVO);
        if (Objects.nonNull(latitude) && Objects.nonNull(baseEntity.getCompanyLongitude())) {
            // 计算两点距离
            double distance = GeoDistance.ARC.calculate(latitude, longitude,
                    baseEntity.getCompanyLatitude(), baseEntity.getCompanyLongitude(), DistanceUnit.KILOMETERS);
            resultVO.setDistance(Double.parseDouble(String.format("%.1f", distance)));
        }
        return resultVO;
    }

}
