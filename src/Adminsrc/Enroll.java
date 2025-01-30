package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class Enroll extends JPanel {
    private Connection connection;

    public Enroll(Connection connection) {
        this.connection = connection;
    }

    public JPanel getEnrollPanel() {
        return createAddStudentPanel();
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));

        // Define custom fonts
        Font labelFont = new Font("Arial", Font.BOLD, 20); // Bold font for labels
        Font inputFont = new Font("Arial", Font.PLAIN, 15); // Plain font for input fields

        // Create UI components
        JTextField txtFirstName = new JTextField();
        JTextField txtLastName = new JTextField();
        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Male", "Female"});
        JTextField txtDob = new JTextField();
        JTextField txtAddress = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtPhoneNumber = new JTextField();
        JComboBox<String> cbDepartment = new JComboBox<>(new String[]{"D001", "D002", "D003", "D004", "D005", " "});
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(Color.green);

        // Create and style labels
        JLabel lblFirstName = new JLabel("First Name:");
        JLabel lblLastName = new JLabel("Last Name:");
        JLabel lblGender = new JLabel("Gender:");
        JLabel lblDob = new JLabel("Date of Birth:");
        JLabel lblAddress = new JLabel("Address:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        JLabel lblDepartment = new JLabel("Department:");

        JLabel[] labels = {lblFirstName, lblLastName, lblGender, lblDob, lblAddress, lblEmail, lblPhoneNumber, lblDepartment};

        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(labelFont);
        }

        // Set fonts for input fields
        txtFirstName.setFont(inputFont);
        txtLastName.setFont(inputFont);
        cbGender.setFont(inputFont);
        txtDob.setFont(inputFont);
        txtEmail.setFont(inputFont);
        txtAddress.setFont(inputFont);
        txtPhoneNumber.setFont(inputFont);
        cbDepartment.setFont(inputFont);
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font for the button

        // Add components to the panel
        panel.add(lblFirstName);
        panel.add(txtFirstName);
        panel.add(lblLastName);
        panel.add(txtLastName);
        panel.add(lblGender);
        panel.add(cbGender);
        panel.add(lblDob);
        panel.add(txtDob);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblAddress);
        panel.add(txtAddress);
        panel.add(lblPhoneNumber);
        panel.add(txtPhoneNumber);
        panel.add(lblDepartment);
        panel.add(cbDepartment);
        panel.add(new JLabel()); // Empty cell
        panel.add(btnSubmit);

        // Button action listener
        btnSubmit.addActionListener(e -> {
            String query = "INSERT INTO student (student_firstname, student_lastname, gender, student_birth, student_email, student_address, phone_number, student_password, department) VALUES (?, ?, ?, ?, ?, ?, ?, '12345', ?)";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, txtFirstName.getText());
                pst.setString(2, txtLastName.getText());
                pst.setString(3, cbGender.getSelectedItem().toString());
                pst.setString(4, txtDob.getText());
                pst.setString(6, txtAddress.getText());
                pst.setString(5, txtEmail.getText());
                pst.setString(7, txtPhoneNumber.getText());
                pst.setString(8, cbDepartment.getSelectedItem().toString());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Student added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

            // Clear the fields after submission
            txtFirstName.setText("");
            txtLastName.setText("");
            cbGender.setSelectedIndex(-1);
            txtDob.setText("");
            txtEmail.setText("");
            txtAddress.setText("");
            txtPhoneNumber.setText("");
            cbDepartment.setSelectedIndex(-1);
            
        });

        return panel;
    }
}
