package cn.mariojd.fantasy.service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jared
 * @date 2018/11/16 16:13
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "nearjobEntityManagerFactory",
        transactionManagerRef = "nearjobTransactionManager",
        basePackages = {"cn.mariojd.fantasy.nearjob.dao"})
public class NearJobDataSourceConfig {

    @Bean(name = "nearjobDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.nearjob")
    public DataSource nearjobDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "nearjobEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean nearjobEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                              @Qualifier("nearjobDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>(2);
        properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("cn.mariojd.fantasy.nearjob.entity")
                .persistenceUnit("nearjob")
                .build();
    }

    @Bean(name = "nearjobTransactionManager")
    public PlatformTransactionManager nearjobTransactionManager(@Qualifier("nearjobEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
        return new JpaTransactionManager(barEntityManagerFactory);
    }

}
