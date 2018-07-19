package factory.abstracts;

/**
 * Created by Jared on 2018/7/18 17:07.
 */
public class CalculatorSubFactory implements ICalculatorFactory {

    /**
     * 获取具有减法运算功能的计算器实例
     *
     * @return 返回当前实例
     */
    @Override
    public ICalculator productCalculator() {
        return new CalculatorSub();
    }

}
