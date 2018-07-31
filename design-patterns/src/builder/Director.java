package builder;

/**
 * Created by Jared on 2018/7/30 15:03.
 * 指挥者类
 */
public class Director {

    /**
     * 构造产品的最终形态
     *
     * @param builder
     */
    void buildProduct(Builder builder) {
        builder.partTwo();
        builder.partOne();
    }

}
