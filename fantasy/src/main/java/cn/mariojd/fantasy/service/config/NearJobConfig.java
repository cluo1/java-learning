package cn.mariojd.fantasy.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;

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
public class NearJobConfig {

    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean nearjobEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(nearjobDataSource());
        em.setPackagesToScan("cn.mariojd.fantasy.nearjob.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>(4);
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource nearjobDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.nearjob.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.nearjob.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.nearjob.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.nearjob.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager nearjobTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(nearjobEntityManagerFactory().getObject());
        return transactionManager;
    }

}
