import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import com.formdev.flatlaf.FlatLightLaf;

public class MainGUI extends JFrame {

    StudentDAO dao = new StudentDAOImpl();
    JPanel contentPanel;

    public MainGUI() {
        FlatLightLaf.setup();

        setTitle("Student Management Dashboard");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel navPanel = new JPanel();
        navPanel.setBackground(new Color(35, 45, 60));
        navPanel.setLayout(new GridLayout(6, 1, 0, 10));
        navPanel.setPreferredSize(new Dimension(220, 0));

        JLabel menuTitle = new JLabel("   Menu");
        menuTitle.setForeground(Color.WHITE);
        menuTitle.setFont(new Font("Arial", Font.BOLD, 20));
        navPanel.add(menuTitle);

        JButton btnAdd = createNavButton("Add Student");
        JButton btnViewAll = createNavButton("View All Students");
        JButton btnUpdate = createNavButton("Update Student");
        JButton btnDelete = createNavButton("Delete Student");
        JButton btnViewById = createNavButton("View Student by ID");

        navPanel.add(btnAdd);
        navPanel.add(btnViewAll);
        navPanel.add(btnUpdate);
        navPanel.add(btnDelete);
        navPanel.add(btnViewById);

        add(navPanel, BorderLayout.WEST);
        JPanel header = new JPanel();
        header.setBackground(new Color(60, 75, 90));
        header.setPreferredSize(new Dimension(0, 60));
        header.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel title = new JLabel("Student Management System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        header.add(title);
        add(header, BorderLayout.NORTH);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
        btnAdd.addActionListener(e -> showAddStudent());
        btnViewAll.addActionListener(e -> showAllStudents());
        btnUpdate.addActionListener(e -> showUpdateStudent());
        btnDelete.addActionListener(e -> showDeleteStudent());
        btnViewById.addActionListener(e -> showViewById());
    }
    private JButton createNavButton(String text) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(55, 65, 80));
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        return btn;
    }
    //Add student
    void showAddStudent() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField grade = new JTextField();

        panel.add(new JLabel("Student ID:"));
        panel.add(id);

        panel.add(new JLabel("Name:"));
        panel.add(name);

        panel.add(new JLabel("Age:"));
        panel.add(age);

        panel.add(new JLabel("Grade:"));
        panel.add(grade);

        int opt = JOptionPane.showConfirmDialog(this, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION);

        if (opt == JOptionPane.OK_OPTION) {
            try {
                dao.addStudent(new Student(
                        Integer.parseInt(id.getText()),
                        name.getText(),
                        Integer.parseInt(age.getText()),
                        grade.getText()
                ));

                JOptionPane.showMessageDialog(this, "Student Added!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid Input!");
            }
        }
    }
    //show all students
    void showAllStudents() {
        java.util.List<Student> list = dao.getAllStudents();

        String[] cols = {"ID", "Name", "Age", "Grade"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        for (Student s : list) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getAge(), s.getGrade()});
        }

        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);

        contentPanel.removeAll();
        contentPanel.add(pane, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

//update student
    void showUpdateStudent() {
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField grade = new JTextField();

        Object[] msg = {"ID to Update:", id, "New Name:", name, "New Age:", age, "New Grade:", grade};

        if (JOptionPane.showConfirmDialog(this, msg, "Update Student", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            try {
                dao.updateStudent(new Student(
                        Integer.parseInt(id.getText()),
                        name.getText(),
                        Integer.parseInt(age.getText()),
                        grade.getText()
                ));

                JOptionPane.showMessageDialog(this, "Student Updated!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Invalid Input!");
            }
        }
    }

 //Delete Student
    void showDeleteStudent() {
        String id = JOptionPane.showInputDialog("Enter Student ID to Delete:");

        try {
            dao.deleteStudent(Integer.parseInt(id));
            JOptionPane.showMessageDialog(this, "Student Deleted!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid ID!");
        }
    }
//View Student By ID
    void showViewById() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");

        try {
            Student s = dao.getStudentById(Integer.parseInt(id));
            if (s != null) {
                JOptionPane.showMessageDialog(this,
                        "\nID: " + s.getId() +
                                "\nName: " + s.getName() +
                                "\nAge: " + s.getAge() +
                                "\nGrade: " + s.getGrade()
                );
            } else {
                JOptionPane.showMessageDialog(this, "Student Not Found!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid ID!");
        }
    }

    public static void main(String[] args) {
        new MainGUI().setVisible(true);
    }
}
