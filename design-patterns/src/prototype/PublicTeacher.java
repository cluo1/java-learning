package prototype;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jared on 2018/7/31 9:45.
 * 公立教师，实现Cloneable并重写clone()
 */
public class PublicTeacher implements Cloneable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 一群学生(一对多关系)
     */
    private List<Student> students;

    public PublicTeacher(String name, List<Student> students) {
        this.name = name;
        List<Student> studentList = new LinkedList<>();
        students.forEach(student -> {
            try {
                studentList.add((Student) student.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        this.students = studentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "PublicTeacher{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
