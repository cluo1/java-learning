package factory.method;

/**
 * Created by Jared on 2018/7/18 17:06.
 */
public interface ICalculatorFactory {

    /**
     * 获取生产某一功能的计算器工厂实例
     *
     * @return 返回生产某一功能计算器的工厂实例
     */
    ICalculator productCalculator();

}
