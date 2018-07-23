package factory.abstracts;

/**
 * Created by Jared on 2018/7/23 13:43.
 * 工厂A: 负责生产青苹果和青梨
 */
public class FactoryA implements IFruitFactory {

    @Override
    public IApple createApple() {
        return new GreenApple();
    }

    @Override
    public IPear createPear() {
        return new GreenPear();
    }

}
