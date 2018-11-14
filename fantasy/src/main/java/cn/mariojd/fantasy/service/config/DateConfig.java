package cn.mariojd.fantasy.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by Jared on 2018/6/25 16:36.
 * <p>
 * {@Link https://www.codetd.com/article/911931}
 */
@Slf4j
@Configuration
public class DateConfig {

    @Bean
    public Converter<Long, Date> longTimestampToDate() {
        return new Converter<Long, Date>() {
            @Override
            public Date convert(Long source) {
                log.info("Source Date {}", source);
                return new Date(source);
            }
        };
    }

    @Bean
    public Converter<String, Date> strTimestampToDate() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                log.info("Source Date {}", source);
                return new Date(Long.parseLong(source));
            }
        };
    }

}
