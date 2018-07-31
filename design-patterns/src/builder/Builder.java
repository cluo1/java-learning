package builder;

/**
 * Created by Jared on 2018/7/30 14:26.
 * 抽象建造者，用于确定产品的组成部件及获取
 */
public interface Builder {

    /**
     * 部件一
     */
    void partOne();

    /**
     * 部件二
     */
    void partTwo();

    /**
     * 获取当前产品
     *
     * @return
     */
    Product getProduct();

}
