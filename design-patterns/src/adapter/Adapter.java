package adapter;

/**
 * Created by Jared on 2018/8/2 11:32.
 * 对象适配器，负责功能转换
 */
public class Adapter extends Target {

    private Adaptee adaptee;

    Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    void console() {
        adaptee.console();
        System.out.println("开始转换");
        super.console();
        System.out.println("结束转换");
    }

}
