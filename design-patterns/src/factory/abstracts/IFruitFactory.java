package factory.abstracts;

/**
 * Created by Jared on 2018/7/23 13:38.
 * 水果工厂类
 */
public interface IFruitFactory {

    /**
     * 创建苹果实例
     *
     * @return
     */
    IApple createApple();

    /**
     * 创建梨子实例
     *
     * @return
     */
    IPear createPear();

}
