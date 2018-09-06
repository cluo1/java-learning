package cn.mariojd.nearjob.controller;

import cn.mariojd.nearjob.model.response.JobDetailVO;
import cn.mariojd.nearjob.service.DetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Jared
 * @date 2018/8/23 9:14
 */
@RestController
@RequestMapping("api/detail")
@Slf4j
public class DetailController {

    @Resource
    private DetailService detailService;

    @GetMapping
    public JobDetailVO findDetail(@RequestParam @NotBlank String positionId,
                                  @RequestParam int jobId) {
        return detailService.findDetail(positionId, jobId);
    }

}
