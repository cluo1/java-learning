package factory.simple;

/**
 * Created by Jared on 2018/7/18 13:24.
 * 减法运算
 */
public class CalculatorSub implements Calculator {

    /**
     * 将两个数进行减法运算
     *
     * @param numberA
     * @param numberB
     * @return 相减结果
     */
    @Override
    public int compute(int numberA, int numberB) {
        return numberA - numberB;
    }

}
