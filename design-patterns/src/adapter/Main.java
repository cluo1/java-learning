package adapter;

/**
 * Created by Jared on 2018/8/2 11:38.
 */
public class Main {

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.console();
    }

}
