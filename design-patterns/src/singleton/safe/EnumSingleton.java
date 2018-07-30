package singleton.safe;

/**
 * Created by Jared on 2018/7/30 10:19.
 * 单例模式：枚举
 * <p>
 * a.线程安全
 * b.非延迟加载
 * c.代码更简洁，支持序列化机制
 * ps: 这是实现单例模式的最佳方式，但未被广泛使用。
 */
public enum EnumSingleton {

    /**
     * 该定义用于返回当前实例
     */
    INSTANCE;


}
