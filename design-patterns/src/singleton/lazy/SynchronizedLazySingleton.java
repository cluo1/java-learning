package singleton.lazy;

/**
 * Created by Jared on 2018/7/24 14:58.
 * 单例模式：懒汉式(synchronized)
 * <p>
 * a.非线程安全
 * b.实现延迟加载
 * c.类加载快但获取对象慢
 */
public class SynchronizedLazySingleton {

    private static SynchronizedLazySingleton instance = null;

    /**
     * 私有构造器
     */
    private SynchronizedLazySingleton() {
    }

    /**
     * 获取当前实例的唯一入口
     * 通过synchronized机制保证多线程安全，但同时也消耗较多的性能
     *
     * @return 当前实例
     */
    public static synchronized SynchronizedLazySingleton getInstance() {
        if (instance == null) {
            instance = new SynchronizedLazySingleton();
        }
        return instance;
    }

}
