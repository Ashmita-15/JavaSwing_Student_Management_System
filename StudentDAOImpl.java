import java.sql.*;
import java.util.*;

public class StudentDAOImpl implements StudentDAO {

    public boolean addStudent(Student s) {
        try (Connection conn = DBConnection.getConnection()) {
            String q = "INSERT INTO students VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getGrade());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("grade")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Student getStudentById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String q = "SELECT * FROM students WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("grade")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateStudent(Student s) {
        try (Connection conn = DBConnection.getConnection()) {
            String q = "UPDATE students SET name=?, age=?, grade=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getGrade());
            ps.setInt(4, s.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String q = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
