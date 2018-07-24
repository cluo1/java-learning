package singleton.hungry;

import factory.method.CalculatorAdd;

/**
 * Created by Jared on 2018/7/24 15:14.
 */
public class Main {

    public static void main(String[] args) {
        HungrySingleton s1 = HungrySingleton.getInstance();
        HungrySingleton s2 = HungrySingleton.getInstance();
        System.out.println(s1.equals(s2));
    }

}
