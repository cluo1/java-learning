package cn.mariojd.fantasy;

import cn.mariojd.fantasy.nearjob.base.BaseDao;
import cn.mariojd.fantasy.nearjob.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableCaching
@SpringBootApplication
public class FantasyApplication {

    public static void main(String[] args) {
        // @See: https://www.jianshu.com/p/1d5257eecf08
        // System.setProperty("es.set.netty.runtime.available.processors", "false");
        ConfigurableApplicationContext context = SpringApplication.run(FantasyApplication.class, args);

        // 通过StringRedisTemplate获得hash key
        StringRedisTemplate redisTemplate = context.getBean("redisTemplate", StringRedisTemplate.class);
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("nearjob:jobs");

        // 通过hash value进行匹配装载对应匹配的Dao
        String[] beanNamesForType = context.getBeanNamesForType(BaseDao.class);
        BaseService.resources = new HashMap<>(entries.size(), 1);
        Arrays.stream(beanNamesForType).forEach(beanName -> entries.entrySet().stream()
                .filter(o -> beanName.toLowerCase().contains((String) o.getKey()))
                .findFirst().ifPresent(o -> {
                    String jobId = (String) o.getValue();
                    BaseDao baseDao = context.getBean(beanName, BaseDao.class);
                    log.info("----->>>Load Bean: " + beanName);
                    BaseService.resources.put(Integer.parseInt(jobId), baseDao);
                }));
    }

    @Bean
    @Primary
    public ConcurrentMapCacheManager concurrentMapCacheManager() {
        return new ConcurrentMapCacheManager();
    }

}

