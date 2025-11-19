import java.sql.*;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean validateUser(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String q = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
