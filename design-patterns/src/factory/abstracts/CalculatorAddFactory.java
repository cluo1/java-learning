package factory.abstracts;

/**
 * Created by Jared on 2018/7/18 17:07.
 */
public class CalculatorAddFactory implements ICalculatorFactory {

    @Override
    public ICalculator productCalculator() {
        return new CalculatorAdd();
    }

}
