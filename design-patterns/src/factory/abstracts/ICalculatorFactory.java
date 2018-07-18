package factory.abstracts;

/**
 * Created by Jared on 2018/7/18 17:06.
 */
public interface ICalculatorFactory {

    /**
     * 生产某一功能的计算器
     *
     * @return
     */
    ICalculator productCalculator();

}
