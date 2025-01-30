package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class AddDepartment extends JPanel {
    private Connection connection;

    public AddDepartment(Connection connection) {
        this.connection = connection;
    }

    public JPanel getAddDepartmentPanel() {
        return createAddDepartmentPanel();
    }

    private JPanel createAddDepartmentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Define custom fonts
        Font labelFont = new Font("Arial", Font.BOLD, 20); // Bold font for labels
        Font inputFont = new Font("Arial", Font.BOLD, 15); // Plain font for input fields

        // Create UI components
        JTextField txtDepartment_id = new JTextField();
        JTextField txtDepartment_name = new JTextField();
        JTextField txtDepartment_head = new JTextField();
        JTextField txtPhone_Num = new JTextField();

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(Color.green);

        // Create and style labels
        JLabel lblDepartment_id = new JLabel("Department ID:");
        JLabel lblDepartment_name = new JLabel("Department Name:");
        JLabel lblDepartment_head = new JLabel("Department Head:");
        JLabel lblPhone_Num = new JLabel("Phone Number:");

        JLabel[] labels = {lblDepartment_id, lblDepartment_name, lblDepartment_head, lblPhone_Num};

        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(labelFont);
        }

        // Set fonts for input fields
        lblDepartment_id.setFont(inputFont);
        lblDepartment_name.setFont(inputFont);
        lblDepartment_head.setFont(inputFont);
        lblPhone_Num.setFont(inputFont);
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font for the button

        // Add components to the panel
        panel.add(lblDepartment_id);
        panel.add(txtDepartment_id);
        panel.add(lblDepartment_name);
        panel.add(txtDepartment_name);
        panel.add(lblDepartment_head);
        panel.add(txtDepartment_head);
        panel.add(lblPhone_Num);
        panel.add(txtPhone_Num);

        panel.add(new JLabel()); // Empty cell
        panel.add(btnSubmit);

        // Button action listener
        btnSubmit.addActionListener(e -> {
            String departmentName = txtDepartment_name.getText();
            if (departmentName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Department Name cannot be empty!");
                return;
            }
        
            String checkQuery = "SELECT COUNT(*) FROM department WHERE department_name = ?";
            String insertQuery = "INSERT INTO department (department_id, department_name, department_head, Dep_head_ph) VALUES (?, ?, ?, ?)";
        
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                 PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
        
                // Check if department name already exists
                checkStmt.setString(1, departmentName);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "Department Name already exists. Please use a different name.");
                    return;
                }
        
                // Insert the new department
                insertStmt.setString(1, txtDepartment_id.getText());
                insertStmt.setString(2, departmentName);
                insertStmt.setString(3, txtDepartment_head.getText());
                insertStmt.setString(4, txtPhone_Num.getText());
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Department added successfully!");
        
                // Clear the fields after submission
                txtDepartment_id.setText("");
                txtDepartment_name.setText("");
                txtDepartment_head.setText("");
                txtPhone_Num.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
        

        return panel;
    }
}
