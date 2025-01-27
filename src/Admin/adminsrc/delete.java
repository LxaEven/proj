package adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class delete {
    private Connection connection;

    public delete(Connection connection) {
        this.connection = connection;
    }

    public JPanel createDeleteStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fonts
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Components
        JLabel lblStudentId = new JLabel("Enter Student ID:");
        lblStudentId.setFont(labelFont);

        JTextField txtStudentId = new JTextField();
        txtStudentId.setFont(inputFont);

        JLabel lblStudentDetails = new JLabel("Student Details:");
        lblStudentDetails.setFont(labelFont);

        JTextArea txtStudentDetails = new JTextArea(10, 20);
        txtStudentDetails.setFont(inputFont);
        txtStudentDetails.setEditable(false);
        txtStudentDetails.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton btnSearch = new JButton("Search");
        JButton btnDelete = new JButton("Delete");
        JButton btnReset = new JButton("Reset");

        btnSearch.setFont(buttonFont);
        btnSearch.setBackground(Color.CYAN);

        btnDelete.setFont(buttonFont);
        btnDelete.setBackground(Color.RED);

        btnReset.setFont(buttonFont);
        btnReset.setBackground(Color.ORANGE);

        // Add components to the panel
        panel.add(lblStudentId);
        panel.add(txtStudentId);
        panel.add(lblStudentDetails);
        panel.add(new JScrollPane(txtStudentDetails));
        panel.add(btnSearch);
        panel.add(btnDelete);
        panel.add(new JLabel()); // Empty cell for layout alignment
        panel.add(btnReset);

        // Search Action
        btnSearch.addActionListener(e -> {
            String studentId = txtStudentId.getText();
            if (studentId.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Student ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String query = "SELECT * FROM student INNER JOIN department ON student.department = department.department_id where student_id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                pst.setInt(1, Integer.parseInt(studentId));
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String details = String.format("ID: %s\nName: %s %s\nGender: %s\nDate of Birth: %s\nAddress: %s\nEmail: %s\nPhone: %s\nDepartment: %s",
                            rs.getInt("student_id"),
                            rs.getString("student_firstname"),
                            rs.getString("student_lastname"),
                            rs.getString("gender"),
                            rs.getString("student_birth"),
                            rs.getString("student_address"),
                            rs.getString("student_email"),
                            rs.getString("phone_number"),
                            rs.getString("department_name"));
                    txtStudentDetails.setText(details);
                } else {
                    txtStudentDetails.setText("");
                    JOptionPane.showMessageDialog(panel, "Student not found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete Action
        btnDelete.addActionListener(e -> {
            String studentId = txtStudentId.getText();
            if (studentId.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Student ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    panel,
                    "Are you sure you want to delete the student with ID: " + studentId + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String query = "DELETE FROM student WHERE student_id = ?";
                try (Connection conn = DBConnect.getConnection();
                     PreparedStatement pst = conn.prepareStatement(query)) {

                    pst.setInt(1, Integer.parseInt(studentId));
                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(panel, "Student deleted successfully!");
                        txtStudentDetails.setText("");
                        txtStudentId.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Student not found!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Reset Action
        btnReset.addActionListener(e -> {
            txtStudentId.setText("");
            txtStudentDetails.setText("");
        });

        return panel;
    }
}
