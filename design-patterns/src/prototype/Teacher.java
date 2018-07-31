package prototype;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jared on 2018/7/31 14:20.
 */
public class Teacher implements Cloneable {

    /**
     * 姓名
     */
    private String name;

    private Teacher(String name) {
        try {
            // 模拟初始化资源耗时较多
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher teacher = new Teacher("李老师");
        // output: teacher = Teacher{name='李老师'}
        System.out.println("teacher = " + teacher);

        // clone is much faster
        Teacher cloneTeacher = (Teacher) teacher.clone();
        // output: cloneTeacher = Teacher{name='李老师'}
        System.out.println("cloneTeacher = " + cloneTeacher);
    }

}
