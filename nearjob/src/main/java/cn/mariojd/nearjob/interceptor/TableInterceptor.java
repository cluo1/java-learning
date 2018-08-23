package cn.mariojd.nearjob.interceptor;

import cn.mariojd.nearjob.base.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.EmptyInterceptor;

import java.io.Serializable;

/**
 * @author Jared
 * @date 2018/8/23 16:48
 */
@Slf4j
public class TableInterceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
        log.info("-----------" + sql);
        return sql;
    }

}
