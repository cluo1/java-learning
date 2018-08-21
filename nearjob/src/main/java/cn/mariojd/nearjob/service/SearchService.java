package cn.mariojd.nearjob.service;

import cn.mariojd.nearjob.model.request.SearchVO;
import cn.mariojd.nearjob.model.response.SearchResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author Jared
 * @date 2018/8/21 10:19
 */
@Slf4j
@Service
public class SearchService {

    public Flux<SearchResultVO> findBySearchVO(SearchVO searchVO) {
        return null;
    }

}
