package singleton.lazy;

/**
 * Created by Jared on 2018/7/24 14:58.
 * 单例模式：懒汉式(双重检测锁。DCL , Double-checked Locking)
 * <p>
 * a.非线程安全
 * b.实现延迟加载
 * c.类加载快但获取对象慢
 */
public class DCLSingleton {

    /**
     * volatile确保了实例的可视性
     */
    private static volatile DCLSingleton instance = null;

    /**
     * 私有构造器
     */
    private DCLSingleton() {
    }

    /**
     * 获取当前实例的唯一入口
     * 通过DCL机制保证多线程安全，同时消耗较少的性能
     *
     * @return 当前实例
     */
    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

}
