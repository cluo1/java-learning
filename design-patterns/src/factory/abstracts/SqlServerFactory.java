package factory.abstracts;

/**
 * Created by Jared on 2018/7/23 13:51.
 * 除绿色水果外的其他水果保存在SqlServer
 */
public class SqlServerFactory implements IFruitFactory {

    @Override
    public IApple createApple() {
        return new RedApple();
    }

    @Override
    public IPear createPear() {
        return new YellowPear();
    }

}
