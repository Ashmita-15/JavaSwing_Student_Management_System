import java.util.*;

public interface StudentDAO {
    boolean addStudent(Student s);
    List<Student> getAllStudents();
    Student getStudentById(int id);
    boolean updateStudent(Student s);
    boolean deleteStudent(int id);
}