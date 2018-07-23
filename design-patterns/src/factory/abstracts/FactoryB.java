package factory.abstracts;

/**
 * Created by Jared on 2018/7/23 13:51.
 * 工厂B: 负责生产红苹果和黄梨
 */
public class FactoryB implements IFruitFactory {

    @Override
    public IApple createApple() {
        return new RedApple();
    }

    @Override
    public IPear createPear() {
        return new YellowPear();
    }

}
