package cn.mariojd.fantasy.service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Jared
 * @date 2018/11/16 15:55
 */
//@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "mpDataSource")
    @Qualifier("mpDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mp")
    public DataSource mpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "nearjobDataSource")
    @Qualifier("nearjobDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.nearjob")
    public DataSource nearjobDataSource() {
        return DataSourceBuilder.create().build();
    }

}
