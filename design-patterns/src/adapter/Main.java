package adapter;

/**
 * Created by Jared on 2018/8/2 11:38.
 */
public class Main {

    public static void main(String[] args) {
        Target target = new Target();
        Adapter adapter = new Adapter(target);

        Request request = new Request(adapter);
        request.console();
    }

}
