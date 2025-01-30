package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class DeleteDepartment{
    private Connection connection;

    public DeleteDepartment(Connection connection) {
        this.connection = connection;
    }

    public JPanel getDeleteDepartmentPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fonts
        Font labelFont = new Font("Arial", Font.BOLD, 15);
        Font inputFont = new Font("Arial", Font.PLAIN, 15);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Components
        JLabel lblDepartment_id = new JLabel("Enter Department ID:");
        lblDepartment_id.setFont(labelFont);

        JTextField txtDepartment_id = new JTextField();
        txtDepartment_id.setFont(inputFont);

        JLabel lblDepartmentDetails = new JLabel("Department Details:");
        lblDepartmentDetails.setFont(labelFont);

        JTextArea txtDepartmentDetails = new JTextArea(10, 20);
        txtDepartmentDetails.setFont(inputFont);
        txtDepartmentDetails.setEditable(false);
        txtDepartmentDetails.setBorder(BorderFactory.createLineBorder(Color.GRAY));

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
        panel.add(lblDepartment_id);
        panel.add(txtDepartment_id);
        panel.add(lblDepartmentDetails);
        panel.add(new JScrollPane(txtDepartmentDetails));
        panel.add(btnSearch);
        panel.add(btnDelete);
        panel.add(new JLabel()); // Empty cell for layout alignment
        panel.add(btnReset);

        // Search Action
        btnSearch.addActionListener(e -> {
            String Department_id = txtDepartment_id.getText();
            if (Department_id.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Department ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String query = "SELECT * FROM department WHERE department_id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                pst.setString(1, Department_id);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String details = String.format(
                        "Department ID: %s\nDepartment Name: %s\nDepartment Head: %s\nPhone Number: %s",
                        rs.getString("department_id"),
                        rs.getString("department_name"),
                        rs.getString("department_head"),
                        rs.getString("Dep_head_ph")
                    );
                    txtDepartmentDetails.setText(details);
                } else {
                    txtDepartmentDetails.setText("");
                    JOptionPane.showMessageDialog(panel, "Department not found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete Action
        btnDelete.addActionListener(e -> {
            String department_id = txtDepartment_id.getText();
            if (department_id.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Department ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                panel,
                "Are you sure you want to delete the Department with ID: " + department_id + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String query = "DELETE FROM department WHERE department_id = ?";
                try (Connection conn = DBConnect.getConnection();
                     PreparedStatement pst = conn.prepareStatement(query)) {

                    pst.setString(1, department_id);
                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(panel, "The Department deleted successfully!");
                        txtDepartmentDetails.setText("");
                        txtDepartment_id.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Department not found!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Reset Action
        btnReset.addActionListener(e -> {
            txtDepartment_id.setText("");
            txtDepartmentDetails.setText("");
        });

        return panel;
    }
}
