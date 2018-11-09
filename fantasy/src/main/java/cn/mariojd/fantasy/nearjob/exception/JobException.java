package cn.mariojd.fantasy.exception;

import cn.mariojd.fantasy.enums.JobEnum;

/**
 * 自定义异常
 *
 * @author Jared
 * @date 2018/8/21 19:17
 */
public class JobException extends RuntimeException {

    private JobEnum jobEnum;

    public JobException(JobEnum jobEnum) {
        this.jobEnum = jobEnum;
    }

    public JobException(String message, JobEnum jobEnum) {
        super(message);
        this.jobEnum = jobEnum;
    }

    JobEnum getJobEnum() {
        return jobEnum;
    }

}
