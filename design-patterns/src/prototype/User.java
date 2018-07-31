package prototype;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jared on 2018/7/31 9:45.
 * User对象，实现Cloneable并重写clone()
 */
public class User implements Cloneable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 衣服
     *
     * @param name
     * @param age
     */
    private List<Cloth> cloths;

    public User(String name, Integer age, List<Cloth> cloths) {
        this.name = name;
        this.age = age;
        this.cloths = cloths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Cloth> getCloths() {
        return cloths;
    }

    public void setCloths(List<Cloth> cloths) {
        List<Cloth> clothList = new LinkedList<>();
        cloths.forEach(cloth -> {
            try {
                Cloth c = (Cloth) cloth.clone();
                clothList.add(c);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        this.cloths = clothList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cloths=" + cloths +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
