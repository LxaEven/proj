package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class AddAdmin extends JPanel {
    private Connection connection;

    public AddAdmin(Connection connection) {
        this.connection = connection;
    }

    public JPanel getAddAdminPanel() {
        return createAddAdminPanel();
    }

    private JPanel createAddAdminPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        // Define custom fonts
        Font labelFont = new Font("Arial", Font.BOLD, 20); // Bold font for labels
        Font inputFont = new Font("Arial", Font.BOLD, 15); // Plain font for input fields

        // Create UI components
        JTextField txtAdmin_id = new JTextField();
        JTextField txtAdmin_name = new JTextField();
        JComboBox<String> cbAdmin_gender = new JComboBox<>(new String[]{"Male", "Female"});
        JTextField txtAdmin_email = new JTextField();
        JTextField txtPassword = new JTextField();

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(Color.green);

        // Create and style labels
        JLabel lblAdmin_id = new JLabel("Admin ID:");
        JLabel lblAdmin_name = new JLabel("Admin Name:");
        JLabel lblAdmin_gender = new JLabel("Admin Gender:");
        JLabel lblPAdmin_email = new JLabel("Admin Email:");
        JLabel lblPassword =  new JLabel("Admin Password");

        JLabel[] labels = {lblAdmin_id, lblAdmin_name, lblAdmin_gender, lblPAdmin_email, lblPassword};

        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(labelFont);
        }

        // Set fonts for input fields
        lblAdmin_id.setFont(inputFont);
        lblAdmin_name.setFont(inputFont);
        lblAdmin_gender.setFont(inputFont);
        lblPAdmin_email.setFont(inputFont);
        lblPassword.setFont(inputFont);
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font for the button

        // Add components to the panel
        panel.add(lblAdmin_id);
        panel.add(txtAdmin_id);
        panel.add(lblAdmin_name);
        panel.add(txtAdmin_name);
        panel.add(lblAdmin_gender);
        panel.add(cbAdmin_gender);
        panel.add(lblPAdmin_email);
        panel.add(txtAdmin_email);
        panel.add(lblPassword);
        panel.add(txtPassword);

        panel.add(new JLabel()); // Empty cell
        panel.add(btnSubmit);

        // Button action listener
        btnSubmit.addActionListener(e -> {
            String AdminName = txtAdmin_name.getText();
            if (AdminName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Admin Name cannot be empty!");
                return;
            }
        
            String checkQuery = "SELECT COUNT(*) FROM admin_account WHERE admin_email = ?";
            String insertQuery = "INSERT INTO admin_account (admin_id, admin_name, admin_gender, admin_email, admin_password) VALUES (?, ?, ?, ?, ?)";
        
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                 PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
        
                // Check if department name already exists
                checkStmt.setString(1, AdminName);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "Admin Email already exists. Please use a different email.");
                    return;
                }
        
                // Insert the new department
                insertStmt.setString(1, txtAdmin_id.getText());
                insertStmt.setString(2, AdminName);
                insertStmt.setString(3, cbAdmin_gender.getSelectedItem().toString());
                insertStmt.setString(4, txtAdmin_email.getText());
                insertStmt.setString(5, txtPassword.getText());
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Admin added successfully!");
        
                // Clear the fields after submission
                txtAdmin_id.setText("");
                txtAdmin_name.setText("");
                cbAdmin_gender.setSelectedIndex(-1);
                txtAdmin_email.setText("");
                txtPassword.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });
        

        return panel;
    }
}
