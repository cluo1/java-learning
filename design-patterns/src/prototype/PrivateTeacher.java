package prototype;

/**
 * Created by Jared on 2018/7/31 9:45.
 * 私人教师，实现Cloneable并重写clone()
 */
public class PrivateTeacher implements Cloneable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 某一学生(一对一关系)
     */
    private Student student;

    public PrivateTeacher() {

    }

    public PrivateTeacher(String name, Student student) throws Exception {
        this.name = name;
        // 深拷贝
        //this.student = (Student) student.clone();
        // 浅拷贝
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) throws Exception {
        this.student = student;
    }

    @Override
    public String toString() {
        return "PrivateTeacher{" +
                "name='" + name + '\'' +
                ", student=" + student +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
