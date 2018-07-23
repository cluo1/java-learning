package factory.abstracts;

/**
 * Created by Jared on 2018/7/23 13:43.
 * 用Mysql存储绿色水果
 */
public class MysqlFactory implements IFruitFactory {

    @Override
    public IApple createApple() {
        return new GreenApple();
    }

    @Override
    public IPear createPear() {
        return new GreenPear();
    }

}
