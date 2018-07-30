package singleton.hungry;

/**
 * Created by Jared on 2018/7/24 14:51.
 * 单例模式：饿汉式
 * <p>
 * a.线程安全
 * b.提前初始化(占用资源)
 * c.类加载慢但获取对象快
 */
public class HungrySingleton {

    private static final HungrySingleton INSTANCE = new HungrySingleton();

    /**
     * 私有构造器
     */
    private HungrySingleton() {
    }

    /**
     * 获取当前实例的唯一入口
     *
     * @return 当前实例
     */
    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

}
