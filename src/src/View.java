import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class View {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private Connection connection;

    public View() {
        initializeDatabaseConnection();
        initializeUI();
    }

    private void initializeDatabaseConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private void initializeUI() {
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 5));
        
        JButton btnAdd = new JButton("Add Student");
        JButton btnView = new JButton("View Students");
        JButton btnUpdate = new JButton("Update Student");
        JButton btnSearch = new JButton("Search Student");
        JButton btnDelete = new JButton("Delete Student");

        menuPanel.add(btnAdd);
        menuPanel.add(btnView);
        menuPanel.add(btnUpdate);
        menuPanel.add(btnSearch);
        menuPanel.add(btnDelete);

        frame.add(menuPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        JPanel addPanel = createAddStudentPanel();
        JPanel viewPanel = createViewStudentPanel();
        JPanel updatePanel = createUpdateStudentPanel();
        JPanel searchPanel = createSearchStudentPanel();
        JPanel deletePanel = createDeleteStudentPanel();

        mainPanel.add(addPanel, "Add");
        mainPanel.add(viewPanel, "View");
        mainPanel.add(updatePanel, "Update");
        mainPanel.add(searchPanel, "Search");
        mainPanel.add(deletePanel, "Delete");

        btnAdd.addActionListener(e -> cardLayout.show(mainPanel, "Add"));
        btnView.addActionListener(e -> cardLayout.show(mainPanel, "View"));
        btnUpdate.addActionListener(e -> cardLayout.show(mainPanel, "Update"));
        btnSearch.addActionListener(e -> cardLayout.show(mainPanel, "Search"));
        btnDelete.addActionListener(e -> cardLayout.show(mainPanel, "Delete"));

        frame.setVisible(true);
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JTextField txtFirstName = new JTextField();
        JTextField txtLastName = new JTextField();
        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Male", "Female"});
        JTextField txtDob = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtAddress = new JTextField();
        JTextField txtPhoneNumber = new JTextField();
        JButton btnSubmit = new JButton("Submit");

        panel.add(new JLabel("First Name:"));
        panel.add(txtFirstName);
        panel.add(new JLabel("Last Name:"));
        panel.add(txtLastName);
        panel.add(new JLabel("Gender:"));
        panel.add(cbGender);
        panel.add(new JLabel("Date of Birth:"));
        panel.add(txtDob);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Address:"));
        panel.add(txtAddress);
        panel.add(new JLabel("Phone Number:"));
        panel.add(txtPhoneNumber);
        panel.add(new JLabel()); // Empty cell
        panel.add(btnSubmit);

        btnSubmit.addActionListener(e -> {
            try {
                String query = "INSERT INTO students (first_name, last_name, gender, dob, email, address, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, txtFirstName.getText());
                pst.setString(2, txtLastName.getText());
                pst.setString(3, cbGender.getSelectedItem().toString());
                pst.setString(4, txtDob.getText());
                pst.setString(5, txtEmail.getText());
                pst.setString(6, txtAddress.getText());
                pst.setString(7, txtPhoneNumber.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Student added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }

    private JPanel createViewStudentPanel() {
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                try {
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM student_db.students");
                    StringBuilder sb = new StringBuilder();
                    while (rs.next()) {
                        sb.append("ID: ").append(rs.getInt("id"))
                          .append(", First Name: ").append(rs.getString("first_name"))
                          .append(", Last Name: ").append(rs.getString("last_name"))
                          .append(", Gender: ").append(rs.getString("gender"))
                          .append(", DOB: ").append(rs.getString("dob"))
                          .append(", Email: ").append(rs.getString("email"))
                          .append(", Address: ").append(rs.getString("address"))
                          .append(", Phone: ").append(rs.getString("phone_number"))
                          .append("\n");
                    }
                    textArea.setText(sb.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        return panel;
    }

    // Placeholder methods for the remaining panels.
    private JPanel createUpdateStudentPanel() {
        return new JPanel();
    }

    private JPanel createSearchStudentPanel() {
        return new JPanel();
    }

    private JPanel createDeleteStudentPanel() {
        return new JPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(View::new);
    }
}
