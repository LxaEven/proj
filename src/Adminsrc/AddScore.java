package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class AddScore extends JPanel {
    private Connection connection;

    public AddScore(Connection connection) {
        this.connection = connection;
    }

    public JPanel getScorePanel() {
        return createAddScorePanel();
    }

    private JPanel createAddScorePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        // Define custom fonts
        Font labelFont = new Font("Arial", Font.BOLD, 20); // Bold font for labels
        Font inputFont = new Font("Arial", Font.PLAIN, 15); // Plain font for input fields

        // Create UI components
        JTextField txtStudent_id = new JTextField();
        JTextField txtDepartment = new JTextField();
        JTextField txtSubject = new JTextField();
        JTextField txtScore = new JTextField();
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(Color.green);

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

        // Set fonts for input fields
        txtStudent_id.setFont(inputFont);
        txtDepartment.setFont(inputFont);
        txtSubject.setFont(inputFont);
        txtScore.setFont(inputFont);
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font for the button

        // Add components to the panel
        panel.add(lblStudent_id);
        panel.add(txtStudent_id);
        panel.add(lblDepartment);
        panel.add(txtDepartment);
        panel.add(lblSubject);
        panel.add(txtSubject);
        panel.add(lblScore);
        panel.add(txtScore);

        panel.add(new JLabel()); // Empty cell
        panel.add(btnSubmit);

        // Button action listener
        btnSubmit.addActionListener(e -> {
            String query = "INSERT INTO score (student_id, department_id, subject_id, student_score) VALUES (?, ?, ?, ?)";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, Integer.parseInt(txtStudent_id.getText()));
                pst.setString(2, txtDepartment.getText());
                pst.setString(3, txtSubject.getText());
                pst.setString(4, txtScore.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Score added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
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
