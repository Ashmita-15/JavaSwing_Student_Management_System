import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatLightLaf;

public class Login extends JFrame {

    LoginDAO loginDAO = new LoginDAOImpl();

    public Login() {
        // Modern theme
        FlatLightLaf.setup();

        setTitle("Login - Student Management");
        setSize(450, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---------------- HEADER ----------------
        JPanel header = new JPanel();
        header.setBackground(new Color(45, 85, 255));
        header.setPreferredSize(new Dimension(0, 70));

        JLabel title = new JLabel("Student Management System");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        header.add(title);
        add(header, BorderLayout.NORTH);

        // ---------------- LOGIN PANEL (CARD) ----------------
        JPanel loginCard = new JPanel();
        loginCard.setLayout(new GridLayout(3, 2, 10, 10));
        loginCard.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        loginCard.setBackground(Color.WHITE);

        // Fields
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        // Styling login button
        loginBtn.setBackground(new Color(45, 85, 255));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 15));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        // Labels
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        loginCard.add(userLabel);
        loginCard.add(userField);

        loginCard.add(passLabel);
        loginCard.add(passField);

        loginCard.add(new JLabel(""));
        loginCard.add(loginBtn);

        add(loginCard, BorderLayout.CENTER);

        // ---------------- LOGIN ACTION ----------------
        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
                return;
            }

            if (loginDAO.validateUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new MainGUI().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
            }
        });
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
