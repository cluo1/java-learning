package cn.mariojd.fantasy.service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Jared
 * @date 2018/11/16 16:00
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mpEntityManagerFactory",
        basePackages = "cn.mariojd.fantasy.mp.dao")
public class MpDataSourceConfig {

    @Primary
    @Bean(name = "mpDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mp")
    public DataSource mpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mpEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mpEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                         @Qualifier("mpDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("cn.mariojd.fantasy.mp.entity")
                .persistenceUnit("mp")
                .build();
    }

    @Primary
    @Bean(name = "mpTransactionManager")
    public PlatformTransactionManager mpTransactionManager(@Qualifier("mpEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
