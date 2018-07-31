package builder;

/**
 * Created by Jared on 2018/7/30 14:27.
 * 具体建造者
 */
public class BuilderImpl implements Builder {

    private Product product = new Product();

    @Override
    public void partOne() {
        product.addPart("部件1");
    }

    @Override
    public void partTwo() {
        product.addPart("部件2");
    }

    @Override
    public Product getProduct() {
        return product;
    }

}
