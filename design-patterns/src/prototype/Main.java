package prototype;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jared on 2018/7/31 9:47.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Student student = new Student("张三");
        PrivateTeacher privateTeacher = new PrivateTeacher("小张", student);
        PrivateTeacher privateTeacher2 = (PrivateTeacher) privateTeacher.clone();

        student.setName("张三学生");
        privateTeacher.setName("小张老师");
        System.out.println(privateTeacher2);

        //-----------------------------------------------------------------------//

        Student student2 = new Student("李四");
        Student student3 = new Student("王五");
        List<Student> studentList = new LinkedList<>();
        studentList.add(student2);
        studentList.add(student3);
        PublicTeacher publicTeacher = new PublicTeacher("小王", studentList);
        PublicTeacher publicTeacher2 = (PublicTeacher) publicTeacher.clone();


        student2.setName("李四学生");
        publicTeacher.setName("小王老师");
        System.out.println(publicTeacher2);
    }


}
