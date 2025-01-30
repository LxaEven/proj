package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class AddCourse extends JPanel {
    private Connection connection;

    public AddCourse(Connection connection) {
        this.connection = connection;
    }

    public JPanel getAddCoursePanel() {
        return createAddCoursePanel();
    }

    private JPanel createAddCoursePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Define custom fonts
        Font labelFont = new Font("Arial", Font.BOLD, 20); // Bold font for labels
        Font inputFont = new Font("Arial", Font.BOLD, 15); // Plain font for input fields

        // Create UI components
        JTextField txtSub_ID = new JTextField();
        JTextField txtSubject = new JTextField();
        JTextField txtHour_week = new JTextField();
        JTextField txtHour_semester = new JTextField();

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(Color.green);

        // Create and style labels
        JLabel lblSub_ID = new JLabel("Subject ID:");
        JLabel lblSubject = new JLabel("Subject:");
        JLabel lblHour_week = new JLabel("Hour per Week:");
        JLabel lblHour_semester = new JLabel("Hour per Semester:");

        JLabel[] labels = {lblSub_ID, lblSubject, lblHour_week, lblHour_semester};

        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(labelFont);
        }

        // Set fonts for input fields
        lblSub_ID.setFont(inputFont);
        lblSubject.setFont(inputFont);
        lblHour_week.setFont(inputFont);
        lblHour_semester.setFont(inputFont);
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font for the button

        // Add components to the panel
        panel.add(lblSub_ID);
        panel.add(txtSub_ID);
        panel.add(lblSubject);
        panel.add(txtSubject);
        panel.add(lblHour_week);
        panel.add(txtHour_week);
        panel.add(lblHour_semester);
        panel.add(txtHour_semester);

        panel.add(new JLabel()); // Empty cell
        panel.add(btnSubmit);

        // Button action listener
        btnSubmit.addActionListener(e -> {
            String subjectName = txtSubject.getText();
            if (subjectName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Subject cannot be empty!");
                return;
            }

            String checkQuery = "SELECT COUNT(*) FROM course WHERE subject = ?";
            String insertQuery = "INSERT INTO course (subject_ID, subject, hour_per_week, hour_per_semester) VALUES (?, ?, ?, ?)";

            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                 PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

                // Check if the subject already exists
                checkStmt.setString(1, subjectName);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "Subject already exists. Please use a different subject name.");
                    return;
                }

                // Insert the new course
                insertStmt.setString(1, txtSub_ID.getText());
                insertStmt.setString(2, subjectName);
                insertStmt.setString(3, txtHour_week.getText());
                insertStmt.setString(4, txtHour_semester.getText());
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Course added successfully!");

                // Clear the fields after submission
                txtSub_ID.setText("");
                txtSubject.setText("");
                txtHour_week.setText("");
                txtHour_semester.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }
}
