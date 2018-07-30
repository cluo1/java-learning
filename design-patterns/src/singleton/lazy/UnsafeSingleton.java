package singleton.lazy;

/**
 * Created by Jared on 2018/7/24 14:58.
 * 单例模式：懒汉式
 * <p>
 * a.非线程安全
 * b.实现延迟加载
 * c.类加载快但获取对象慢
 */
public class UnsafeSingleton {

    private static UnsafeSingleton instance = null;

    /**
     * 私有构造器
     */
    private UnsafeSingleton() {
    }

    /**
     * 获取当前实例的唯一入口
     * 多线程下非线程安全
     *
     * @return 当前实例
     */
    public static UnsafeSingleton getInstance() {
        if (instance == null) {
            instance = new UnsafeSingleton();
        }
        return instance;
    }

}
