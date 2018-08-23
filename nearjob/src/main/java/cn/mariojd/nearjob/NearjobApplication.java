package cn.mariojd.nearjob;

import cn.mariojd.nearjob.base.BaseDao;
import cn.mariojd.nearjob.base.BaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class NearjobApplication {

    public static void main(String[] args) {
        // @See: https://www.jianshu.com/p/1d5257eecf08
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        ConfigurableApplicationContext context = SpringApplication.run(NearjobApplication.class, args);
        StringRedisTemplate redisTemplate = context.getBean("redisTemplate", StringRedisTemplate.class);
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("nearjob:jobs");
        String[] beanNamesForType = context.getBeanNamesForType(BaseDao.class);
        BaseService.resources = new HashMap<>(entries.size());
        for (String beanName : beanNamesForType) {
            BaseDao baseDao = context.getBean(beanName, BaseDao.class);
            entries.entrySet().stream().filter(o -> beanName.contains((String) o.getKey())).findFirst().ifPresent(o -> {
                String jobId = (String) o.getValue();
                BaseService.resources.put(Integer.parseInt(jobId), baseDao);
            });
        }
    }
}
