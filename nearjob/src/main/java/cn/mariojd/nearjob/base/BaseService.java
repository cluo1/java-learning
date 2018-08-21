package cn.mariojd.nearjob.base;

import cn.mariojd.nearjob.dao.JavaDao;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2018/8/21 16:49
 */
public class BaseService {

    @Resource
    protected JavaDao javaDao;

}
