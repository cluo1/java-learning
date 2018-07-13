package cn.mariojd.dao.cache;

import cn.mariojd.entity.Seckill;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Mario
 * 后端redis缓存Dao
 */
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    //Redis服务器IP
    private static String IP = "XXX.XXX";

    //Redis的端口号
    private static int PORT = 6379;

    //访问密码
    private static String AUTH = "redis123";

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAXTOTAL = 32;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAXIDLE = 16;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAXWAITMILLIS = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    //超时时间
    private static int TIMEOUT = 10000;

    //jedisPool连接池
    private static JedisPool jedisPool = null;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAXTOTAL);
            config.setMaxIdle(MAXIDLE);
            config.setMaxWaitMillis(MAXWAITMILLIS);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, IP, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从缓存中查找并获取对象，没有就返回null
     *
     * @param sid
     * @return Seckill
     */
    public Seckill getSeckillBySid(Integer sid) {
        //进行redis逻辑操作
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + sid;
                //redis并没有实现内部序列化操作，需要获得byte[]进行反序列化Object(Seckill)
                //这里采用自定义序列化(protostuff：pojo)进行操作
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    //创建一个可以被赋值的空对象
                    Seckill seckill = schema.newMessage();
                    //使用工具类进行赋值操作
                    ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                    //将反序列后的seckill返回
                    return seckill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 缓存中不存在对象则先从数据库中获取该对象再放入redis中
     *
     * @param seckill
     * @return String
     */
    public String putSeckill(Seckill seckill) {
        //将Object(Seckill)序列化为byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckill.getSid();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存，单位秒，这里设置为1小时
                int timeout = 60 * 60;
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
