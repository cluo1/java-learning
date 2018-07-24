package singleton.lazy;

/**
 * Created by Jared on 2018/7/24 14:58.
 * 单例模式：懒汉式(DCL , Double-checked Locking)
 * <p>
 * a.非线程安全
 * b.实现延迟加载
 * c.类加载快但获取对象慢
 */
public class DCLLazySingleton {

    private static volatile DCLLazySingleton instance = null;

    /**
     * 私有构造器
     */
    private DCLLazySingleton() {
    }

    /**
     * 获取当前实例的唯一入口
     * 通过DCL机制保证多线程安全，同时消耗较少的性能
     *
     * @return 当前实例
     */
    public static DCLLazySingleton getInstance() {
        if (instance == null) {
            synchronized (DCLLazySingleton.class) {
                if (instance == null) {
                    instance = new DCLLazySingleton();
                }
            }
        }
        return instance;
    }

}
