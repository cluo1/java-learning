package singleton.safe;

/**
 * Created by Jared on 2018/7/30 10:08.
 * 单例模式：登记式(静态内部类)
 * <p>
 * a.线程安全
 * b.实现延迟加载
 */
public class InnerClassSingleton {

    /**
     * 静态内部类，用于初始化调用时实例化当前 instance
     */
    private static class InnerStaticClassHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    /**
     * 私有构造器
     */
    private InnerClassSingleton() {
    }

    /**
     * 获取当前实例的唯一入口
     * 通过对静态域使用延迟初始化达到延迟加载的效果
     * (只有通过显式调用 getInstance 方法时，才会显式装载 InnerStaticClassHolder 类，从而实例化 instance)
     *
     * @return 当前实例
     */
    private static InnerClassSingleton getInstance() {
        return InnerStaticClassHolder.INSTANCE;
    }

}
