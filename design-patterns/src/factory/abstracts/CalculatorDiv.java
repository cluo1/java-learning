package factory.abstracts;

/**
 * Created by Jared on 2018/7/18 13:24.
 * 除法运算
 */
public class CalculatorDiv implements ICalculator {

    /**
     * 将两个数进行除法运算
     *
     * @param numberA
     * @param numberB
     * @return 相除结果
     */
    @Override
    public int compute(int numberA, int numberB) {
        return numberA / numberB;
    }

}
