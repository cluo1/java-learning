package builder;

/**
 * Created by Jared on 2018/7/30 15:03.
 * 指挥者类（控制建造流程）
 */
public class Director {

    /**
     * 控制并构造所需产品的最终形态
     *
     * @param builder
     */
    void buildProduct(Builder builder) {
        builder.partB();
        builder.partA();
    }

}
