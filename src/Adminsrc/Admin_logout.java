package Adminsrc;
import java.awt.*;
import java.sql.Connection;
import javax.swing.*;

public class Admin_logout extends JPanel {
    private Connection connection;

    public Admin_logout(Connection connection) {
        this.connection = connection;
        setLayout(new BorderLayout());
    }

    public JPanel getAdmin_logoutPanel() {
        return createAdmin_logoutPanel();
    }

    private JPanel createAdmin_logoutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Message Label
        // JLabel lblMessage = new JLabel("Are you sure you want to logout?", SwingConstants.CENTER);
        // lblMessage.setFont(new Font("Arial", Font.BOLD, 18));
        // panel.add(lblMessage, BorderLayout.CENTER);
        int confirm = JOptionPane.showConfirmDialog(
            panel,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            performLogout();
        }

        // Button Panel
        // JPanel buttonPanel = new JPanel();
        // buttonPanel.setLayout(new FlowLayout());

        // Logout Button
        // JButton btnLogout = new JButton("Logout");
        // btnLogout.setBackground(Color.RED);
        // btnLogout.setForeground(Color.WHITE);
        // btnLogout.setFont(new Font("Arial", Font.BOLD, 14));
        // btnLogout.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         performLogout();
        //     }
        // });

        // Cancel Button
        // JButton btnCancel = new JButton("Cancel");
        // btnCancel.setBackground(Color.GRAY);
        // btnCancel.setForeground(Color.WHITE);
        // btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        // btnCancel.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Remove the logout panel and refresh parent
        //         Container parent = Admin_logout.this.getParent();
        //         if (parent != null) {
        //             parent.remove(Admin_logout.this);
        //             parent.revalidate();
        //             parent.repaint();
        //         }
        //     }
        // });

        // Add buttons to button panel
        // buttonPanel.add(btnLogout);
        // buttonPanel.add(btnCancel);

        // Add button panel to main panel
        // panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void performLogout() {
        // Clear session or perform logout actions
        JOptionPane.showMessageDialog(this, "You have been logged out successfully.", "Logout", JOptionPane.INFORMATION_MESSAGE);

        // Close the current frame (if any) or navigate to login screen
        SwingUtilities.getWindowAncestor(this).dispose(); // Close the current window
 //       new AdminLogin(); // Replace with your login class
    }



}
