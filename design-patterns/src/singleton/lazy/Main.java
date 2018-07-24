package singleton.lazy;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jared on 2018/7/24 15:16.
 */
public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            LazySingleton instance = LazySingleton.getInstance();
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(instance.hashCode());
            }).start();
        }
        LazySingleton s2 = LazySingleton.getInstance();
        //System.out.println(s1.equals(s2));
    }

}
