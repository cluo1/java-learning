package builder;

/**
 * Created by Jared on 2018/7/30 14:26.
 * 抽象建造者，用于确定产品的组成部件及获取
 */
public interface Builder {

    /**
     * 部件A
     */
    void partA();

    /**
     * 部件B
     */
    void partB();

    /**
     * 获取产品
     *
     * @return
     */
    Product getProduct();

}
