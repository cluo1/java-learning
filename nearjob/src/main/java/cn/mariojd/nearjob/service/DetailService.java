package cn.mariojd.nearjob.service;

import cn.mariojd.nearjob.base.BaseService;
import cn.mariojd.nearjob.model.response.JobDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Jared
 * @date 2018/8/23 9:15
 */
@Slf4j
@Service
public class DetailService extends BaseService {

    /**
     * 获取Job详情数据
     *
     * @param positionId
     * @param jobId
     * @return
     */
    public Page<JobDetailVO> findDetail(String positionId, int jobId) {
        return null;
    }
}
