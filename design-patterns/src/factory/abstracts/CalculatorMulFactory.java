package factory.abstracts;

/**
 * Created by Jared on 2018/7/18 17:07.
 */
public class CalculatorMulFactory implements ICalculatorFactory {

    @Override
    public ICalculator productCalculator() {
        return new CalculatorMul();
    }

}
