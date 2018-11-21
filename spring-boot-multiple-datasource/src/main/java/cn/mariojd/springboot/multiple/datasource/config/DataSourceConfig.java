package cn.mariojd.springboot.multiple.datasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @author Jared
 * @date 2018/11/21 19:41
 * @blog: https://blog.mariojd.cn/
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.jpa.datasource.hikari.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.jpa.datasource.hikari.postgres")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }

}
