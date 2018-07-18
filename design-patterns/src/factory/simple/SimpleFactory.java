package factory.simple;

/**
 * Created by Jared on 2018/7/18 13:19.
 * 简单工厂
 */
public class SimpleFactory {

    /**
     * 根据操作指令获取计算器操作对象
     *
     * @param type 操作指令
     * @return 具有某一功能的计算器
     */
    public static Calculator calculate(TypeEnum type) {
        Calculator calculator = null;
        switch (type) {
            case ADD:
                calculator = new CalculatorAdd();
                break;
            case SUB:
                calculator = new CalculatorSub();
                break;
            case MUL:
                calculator = new CalculatorMul();
                break;
            case DIV:
                calculator = new CalculatorDiv();
                break;
            default:
                break;
        }
        return calculator;
    }

}
