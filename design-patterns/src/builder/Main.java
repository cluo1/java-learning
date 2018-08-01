package builder;

/**
 * Created by Jared on 2018/7/31 9:30.
 */
public class Main {

    public static void main(String[] args) {
        // 指挥者
        Director director = new Director();
        Builder builder = new BuilderImpl();
        // 由指挥者控制建造过程
        director.buildProduct(builder);

        // 建造完成后返回最终产品
        Product product = builder.getProduct();
        // 调用展示
        product.show();
    }

}
