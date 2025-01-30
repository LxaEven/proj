package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;



public class UpdateDepartment extends JPanel {
    private Connection connection;

    public UpdateDepartment(Connection connection) {
        this.connection = connection;
    }

    public JPanel getUpdateDepartmentPanel() {
        return createUpdateDepartmentPanel();
    }

    private JPanel createUpdateDepartmentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fonts for UI elements
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font inputFont = new Font("Arial", Font.PLAIN, 15);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Input fields
        JTextField txtDepartment_id = new JTextField();
        JTextField txtDepartment_name = new JTextField();
        JTextField txtDepartment_head = new JTextField();
        JTextField txtPhone_Num = new JTextField();

        // Buttons
        JButton btnSearch = new JButton("Search");
        JButton btnUpdate = new JButton("Update");

        // Set fonts for input fields and buttons
        txtDepartment_id.setFont(inputFont);
        txtDepartment_name.setFont(inputFont);
        txtDepartment_head.setFont(inputFont);
        txtPhone_Num.setFont(inputFont);

        btnSearch.setFont(buttonFont);
        btnSearch.setBackground(Color.CYAN);
        btnUpdate.setFont(buttonFont);
        btnUpdate.setBackground(Color.GREEN);

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

        // Add components to the panel
        panel.add(lblDepartment_id);
        panel.add(txtDepartment_id);
        panel.add(lblDepartment_name);
        panel.add(txtDepartment_name);
        panel.add(lblDepartment_head);
        panel.add(txtDepartment_head);
        panel.add(lblPhone_Num);
        panel.add(txtPhone_Num);
        panel.add(btnSearch);
        panel.add(btnUpdate);

        // Search Action
        btnSearch.addActionListener(e -> {
            String query = "SELECT * FROM department WHERE department_id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
           
                pst.setString(1, txtDepartment_id.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    txtDepartment_name.setText(rs.getString("department_name"));
                    txtDepartment_head.setText(rs.getString("department_head"));
                    txtPhone_Num.setText(rs.getString("Dep_head_ph"));
                } else {
                    JOptionPane.showMessageDialog(panel, "Department not found!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Update Action
         btnUpdate.addActionListener(e -> {
            String checkQuery = "SELECT COUNT(*) FROM department WHERE department_name = ? AND department_id != ?";
            String updatequery = "UPDATE department SET department_name = ?, department_head = ?, Dep_head_ph = ? WHERE department_id = ?";
            try (Connection conn = DBConnect.getConnection();
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                PreparedStatement updateStmt = conn.prepareStatement(updatequery)) {

                // Check if the new subject name already exists for a different course
                checkStmt.setString(1, txtDepartment_name.getText());
                checkStmt.setString(2, txtDepartment_id.getText());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(panel, "Department name already exists. Please use a different name.");
                    return;
                }

                // Perform the update
                updateStmt.setString(1, txtDepartment_name.getText());
                updateStmt.setString(2, txtDepartment_head.getText());
                updateStmt.setString(3, txtPhone_Num.getText());
                updateStmt.setString(4, txtDepartment_id.getText());


                int rowsAffected = updateStmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(panel, "The Department updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(panel, "No records were updated!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

            // Clear the fields after submission
            txtDepartment_id.setText("");
            txtDepartment_name.setText("");
            txtDepartment_head.setText("");
            txtPhone_Num.setText("");

        });

        return panel;
    }
}

