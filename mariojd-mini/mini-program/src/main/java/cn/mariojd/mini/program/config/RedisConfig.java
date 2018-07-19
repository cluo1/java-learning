package cn.mariojd.mini.program.config;

import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 */
@Configuration
public class RedisConfig {

//    @Bean(name = "redis")
//    public RedisTemplate setRedis(RedisConnectionFactory redisConnectionFactory) {
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        RedisTemplate<String, Object> t = new RedisTemplate<>();
//        t.setConnectionFactory(redisConnectionFactory);
//        t.setKeySerializer(t.getStringSerializer());
//        t.setHashKeySerializer(t.getStringSerializer());
//        t.setDefaultSerializer(jackson2JsonRedisSerializer);
//        t.afterPropertiesSet();
//        return t;
//    }
//
//    /**
//     * 默认缓存3天
//     */
//    @Bean
//    public RedisCacheManager setRedisCacheManager(@Qualifier("redis") RedisTemplate redisTemplate) {
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
//        redisCacheManager.setDefaultExpiration(TimeUnit.DAYS.toSeconds(3));
//        redisCacheManager.setCachePrefix(new DefaultRedisCachePrefix());
//        redisCacheManager.setUsePrefix(true);
//        redisCacheManager.setExpires(new HashMap<String, Long>(2) {
//            {
//                this.put("cms:company:statistic", TimeUnit.MINUTES.toSeconds(10));
//                this.put("cms:product:statistic", TimeUnit.HOURS.toSeconds(12));
//            }
//        });
//        return redisCacheManager;
//    }

}
