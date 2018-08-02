package adapter;

/**
 * Created by Jared on 2018/8/2 11:32.
 * 适配器，负责功能转换
 */
public class Adapter {

    private Target target;

    Adapter(Target target) {
        this.target = target;
    }

    void transfer() {
        System.out.println("适配器开始转换电压");
        target.console();
        System.out.println("适配器结束电压转换");
    }

}
