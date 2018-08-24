package cn.mariojd.nearjob.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Jared
 * @date 2018/8/24 10:56
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NearJob {

    /**
     * 对应Job的id值
     *
     * @return
     */
    int value() default 0;

}
