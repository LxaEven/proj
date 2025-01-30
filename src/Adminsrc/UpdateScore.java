package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class UpdateScore extends JPanel {

    private Connection connection;

    public UpdateScore(Connection connection) {
        this.connection = connection;
    }

    public JPanel createUpdateScorePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fonts for UI elements
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font inputFont = new Font("Arial", Font.PLAIN, 15);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Input fields
        JTextField txtStudent_id = new JTextField();
        JTextField txtDepartment = new JTextField();
        JTextField txtSubject = new JTextField();
        JTextField txtScore = new JTextField();

        // Buttons
        JButton btnSearch = new JButton("Search");
        JButton btnUpdate = new JButton("Update");

        // Set fonts for input fields and buttons
        txtStudent_id.setFont(inputFont);
        txtDepartment.setFont(inputFont);
        txtSubject.setFont(inputFont);
        txtScore.setFont(inputFont);

        btnSearch.setFont(buttonFont);
        btnSearch.setBackground(Color.CYAN);
        btnUpdate.setFont(buttonFont);
        btnUpdate.setBackground(Color.GREEN);

        // Create and style labels
        JLabel lblStudent_id = new JLabel("Student ID:");
        JLabel lblDepartment = new JLabel("Department:");
        JLabel lblSubject = new JLabel("Subject:");
        JLabel lblScore = new JLabel("Score:");


        JLabel[] labels = {lblStudent_id, lblDepartment, lblSubject, lblScore};

        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(labelFont);
        }

        // Add components to the panel
        panel.add(lblStudent_id);
        panel.add(txtStudent_id);
        panel.add(lblDepartment);
        panel.add(txtDepartment);
        panel.add(lblSubject);
        panel.add(txtSubject);
        panel.add(lblScore);
        panel.add(txtScore);

        panel.add(btnSearch);
        panel.add(btnUpdate);

        // Search Action
        btnSearch.addActionListener(e -> {
            String query = "SELECT * FROM score WHERE student_id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, Integer.parseInt(txtStudent_id.getText()));
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    txtStudent_id.setText(rs.getString("student_id"));
                    txtDepartment.setText(rs.getString("department_id"));
                    txtSubject.setText(rs.getString("subject_id"));
                    txtScore.setText(rs.getString("student_score"));
                } else {
                    JOptionPane.showMessageDialog(panel, "Student not found!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Update Action
        btnUpdate.addActionListener(e -> {
            String query = "UPDATE score SET department_id = ?, subject_id = ?, student_score = ? WHERE student_id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                pst.setString(1, txtDepartment.getText());
                pst.setString(2, txtSubject.getText());
                pst.setString(3, txtScore.getText());
                pst.setInt(4, Integer.parseInt(txtStudent_id.getText()));
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(panel, "Score updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(panel, "No records were updated!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

            // Clear the fields after submission
            txtStudent_id.setText("");
            txtDepartment.setText("");
            txtSubject.setText("");
            txtScore.setText("");

        });

        return panel;
    }
}
