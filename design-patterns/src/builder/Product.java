package builder;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jared on 2018/7/31 9:17.
 * 表示具体的产品，由多个部件组成
 */
public class Product {

    /**
     * 存放产品每个部件的集合
     */
    private List<String> parts = new LinkedList<>();

    /**
     * 添加某一部件到该集合中
     *
     * @param part
     */
    void addPart(String part) {
        parts.add(part);
    }

    /**
     * 展示当前产品的最终形态
     */
    void show() {
        parts.forEach(part -> System.out.print(part + "  "));
    }

}
