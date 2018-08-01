package builder;

/**
 * Created by Jared on 2018/7/30 14:27.
 * 具体建造者
 */
public class BuilderImpl implements Builder {

    private Product product = new Product();

    @Override
    public void partA() {
        product.addPart("部件A");
    }

    @Override
    public void partB() {
        product.addPart("部件B");
    }

    @Override
    public Product getProduct() {
        return product;
    }

}
