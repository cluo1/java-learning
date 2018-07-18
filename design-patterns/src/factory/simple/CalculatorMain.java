package factory.simple;

import java.util.Objects;

/**
 * Created by Jared on 2018/7/18 13:34.
 */
public class CalculatorMain {

    public static void main(String[] args) {
        int numberA = 23;
        int numberB = 18;

        // 从简单工厂获取任意拥有某一功能的计算器
        ICalculator calculate = CalculatorFactory.calculate(TypeEnum.ADD);
        if (Objects.nonNull(calculate)) {
            int result = calculate.compute(numberA, numberB);
            System.out.println("result = " + result);
        }
    }

}
