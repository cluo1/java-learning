package cn.mariojd.fantasy.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by Jared on 2018/6/25 16:36.
 * <p>
 * {@Link https://www.codetd.com/article/911931}
 */
@Configuration
public class DateConfig {

    @Bean
    public Converter<Long, Date> longTimestampToDate() {
        return new Converter<Long, Date>() {
            @Override
            public Date convert(Long source) {
                return new Date(source);
            }
        };
    }

}
