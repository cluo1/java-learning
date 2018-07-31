package builder;

/**
 * Created by Jared on 2018/7/31 9:30.
 */
public class Main {

    public static void main(String[] args) {
        Director director = new Director();
        Builder builder = new BuilderImpl();
        director.buildProduct(builder);

        Product product = builder.getProduct();
        product.show();
    }

}
