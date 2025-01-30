package Adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

import main.DBConnect;

public class DeleteAdmin{
    private Connection connection;

    public DeleteAdmin(Connection connection) {
        this.connection = connection;
    }

    public JPanel getDeleteAdminPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fonts
        Font labelFont = new Font("Arial", Font.BOLD, 15);
        Font inputFont = new Font("Arial", Font.PLAIN, 15);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Components
        JLabel lblAdmin_id = new JLabel("Enter Admin ID:");
        lblAdmin_id.setFont(labelFont);

        JTextField txtAdmin_id = new JTextField();
        txtAdmin_id.setFont(inputFont);

        JLabel lblAdminDetails = new JLabel("Admin Details:");
        lblAdminDetails.setFont(labelFont);

        JTextArea txtAdminDetails = new JTextArea(10, 20);
        txtAdminDetails.setFont(inputFont);
        txtAdminDetails.setEditable(false);
        txtAdminDetails.setBorder(BorderFactory.createLineBorder(Color.GRAY));

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
        panel.add(lblAdmin_id);
        panel.add(txtAdmin_id);
        panel.add(lblAdminDetails);
        panel.add(new JScrollPane(txtAdminDetails));
        panel.add(btnSearch);
        panel.add(btnDelete);
        panel.add(new JLabel()); // Empty cell for layout alignment
        panel.add(btnReset);

        // Search Action
        btnSearch.addActionListener(e -> {
            String Department_id = txtAdmin_id.getText();
            if (Department_id.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Admin ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String query = "SELECT * FROM admin_account WHERE admin_id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                pst.setString(1, Department_id);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String details = String.format(
                        "Admin ID: %s\nAdmin Name: %s\nAdmin Gender: %s\nAdmin Email: %s\nAdmin Password: %s",
                        rs.getString("admin_id"),
                        rs.getString("admin_name"),
                        rs.getString("admin_gender"),
                        rs.getString("admin_email"),
                        rs.getString("admin_password")
                    );
                    txtAdminDetails.setText(details);
                } else {
                    txtAdminDetails.setText("");
                    JOptionPane.showMessageDialog(panel, "Admin not found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete Action
        btnDelete.addActionListener(e -> {
            String admin_id = txtAdmin_id.getText();
            if (admin_id.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Admin ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                panel,
                "Are you sure you want to delete the Admin with ID: " + admin_id + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String query = "DELETE FROM admin_account WHERE admin_id = ?";
                try (Connection conn = DBConnect.getConnection();
                     PreparedStatement pst = conn.prepareStatement(query)) {

                    pst.setString(1, admin_id);
                    int rowsAffected = pst.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(panel, "The Admin deleted successfully!");
                        txtAdminDetails.setText("");
                        txtAdmin_id.setText("");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Admin not found!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Reset Action
        btnReset.addActionListener(e -> {
            txtAdmin_id.setText("");
            txtAdminDetails.setText("");
        });

        return panel;
    }
}
