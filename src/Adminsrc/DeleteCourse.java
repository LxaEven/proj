package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class DeleteCourse {
    private Connection connection;

    public DeleteCourse(Connection connection) {
        this.connection = connection;
    }

    public JPanel createDeleteCoursePanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fonts
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Components
        JLabel lblCourseId = new JLabel("Enter Subject ID:");
        lblCourseId.setFont(labelFont);

        JTextField txtCourseId = new JTextField();
        txtCourseId.setFont(inputFont);

        JLabel lblCourseDetails = new JLabel("Course Details:");
        lblCourseDetails.setFont(labelFont);

        JTextArea txtCourseDetails = new JTextArea(10, 20);
        txtCourseDetails.setFont(inputFont);
        txtCourseDetails.setEditable(false);
        txtCourseDetails.setBorder(BorderFactory.createLineBorder(Color.GRAY));

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
        panel.add(lblCourseId);
        panel.add(txtCourseId);
        panel.add(lblCourseDetails);
        panel.add(new JScrollPane(txtCourseDetails));
        panel.add(btnSearch);
        panel.add(btnDelete);
        panel.add(new JLabel()); // Empty cell for layout alignment
        panel.add(btnReset);

        // Search Action
        btnSearch.addActionListener(e -> {
            String courseId = txtCourseId.getText();
            if (courseId.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Subject ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String query = "SELECT * FROM course WHERE subject_ID = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                pst.setString(1, courseId);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String details = String.format(
                        "Subject ID: %s\nSubject: %s\nHours per Week: %d\nHours per Semester: %d",
                        rs.getString("subject_ID"),
                        rs.getString("subject"),
                        rs.getInt("hour_per_week"),
                        rs.getInt("hour_per_semester")
                    );
                    txtCourseDetails.setText(details);
                } else {
                    txtCourseDetails.setText("");
                    JOptionPane.showMessageDialog(panel, "Subject not found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete Action
        btnDelete.addActionListener(e -> {
            String courseId = txtCourseId.getText();
            if (courseId.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Subject ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                panel,
                "Are you sure you want to delete the Subject with ID: " + courseId + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String query = "DELETE FROM course WHERE subject_ID = ?";
                try (Connection conn = DBConnect.getConnection();
                     PreparedStatement pst = conn.prepareStatement(query)) {

                    pst.setString(1, courseId);
                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(panel, "The Subject deleted successfully!");
                        txtCourseDetails.setText("");
                        txtCourseId.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Subject not found!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Reset Action
        btnReset.addActionListener(e -> {
            txtCourseId.setText("");
            txtCourseDetails.setText("");
        });

        return panel;
    }
}
