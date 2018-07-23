package factory.abstracts;

/**
 * Created by Jared on 2018/7/23 13:53.
 */
public class Main {

    public static void main(String[] args) {
        IFruitFactory factoryA = new FactoryA();
        IApple apple = factoryA.createApple();
        apple.describe();
        IPear pear = factoryA.createPear();
        pear.describe();

        System.out.println("-------------------");

        IFruitFactory factoryB = new FactoryB();
        apple = factoryB.createApple();
        apple.describe();
        pear = factoryB.createPear();
        pear.describe();
    }

}
