package factory.abstracts;

/**
 * Created by Jared on 2018/7/18 13:34.
 */
public class CalculatorMain {

    public static void main(String[] args) {
        int numberA = 23;
        int numberB = 18;

        // 从抽象工厂获取生产某一功能计算器的工厂实例
        ICalculatorFactory calculatorFactory = new CalculatorSubFactory();
        // 从当前工厂获取制造某一功能的计算器实例
        ICalculator calculator = calculatorFactory.productCalculator();
        // 计算
        int result = calculator.compute(numberA, numberB);
        System.out.println("result = " + result);
    }

}
