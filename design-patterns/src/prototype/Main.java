package prototype;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jared on 2018/7/31 9:47.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Cloth c1 = new Cloth("上衣");
        Cloth c2 = new Cloth("裤子");
        List<Cloth> cloths = new LinkedList<>();
        cloths.add(c1);
        cloths.add(c2);

        User user = new User("张三", 20, cloths);
        User cloneUser = (User) user.clone();
        System.out.println(cloneUser);

        user.setAge(22);
        Cloth c3 = new Cloth("鞋子");
        cloths.add(c3);
        System.out.println(cloneUser);
    }


}
