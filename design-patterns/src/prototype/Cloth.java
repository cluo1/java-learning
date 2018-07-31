package prototype;

/**
 * Created by Jared on 2018/7/31 10:07.
 */
public class Cloth implements Cloneable{

    private String name;

    public Cloth(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cloth{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
