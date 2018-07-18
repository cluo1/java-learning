package factory.abstracts;

/**
 * Created by Jared on 2018/7/18 13:22.
 * 计算器
 */
public interface ICalculator {

    /**
     * 计算numberA和numberB并返回操作结果
     *
     * @param numberA
     * @param numberB
     * @return 操作结果
     */
    int compute(int numberA, int numberB);

}
