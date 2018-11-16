package cn.mariojd.fantasy.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
 * @date 2018/11/16 16:00
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mpEntityManagerFactory",
        transactionManagerRef = "mpTransactionManager",
        basePackages = "cn.mariojd.fantasy.mp.dao")
public class MpConfig {

    @Resource
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mpEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mpDataSource());
        em.setPackagesToScan("cn.mariojd.fantasy.mp.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>(4);
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public DataSource mpDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.mp.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.mp.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.mp.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.mp.password"));
        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager mpTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mpEntityManagerFactory().getObject());
        return transactionManager;
    }

}
