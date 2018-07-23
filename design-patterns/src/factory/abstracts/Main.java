package factory.abstracts;

/**
 * Created by Jared on 2018/7/23 13:53.
 */
public class Main {

    public static void main(String[] args) {
        IFruitFactory mysqlFactory = new MysqlFactory();
        IApple apple = mysqlFactory.createApple();
        apple.buyAppleForSave();
        IPear pear = mysqlFactory.createPear();
        pear.buyPearForSave();

        IFruitFactory sqlServerFactory = new SqlServerFactory();
        apple = sqlServerFactory.createApple();
        apple.buyAppleForSave();
        pear = sqlServerFactory.createPear();
        pear.buyPearForSave();
    }

}
