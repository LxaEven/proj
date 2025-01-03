import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangePassword extends JPanel {
    public ChangePassword(JPanel mainPanel) {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Student > Change Password");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        JLabel oldPasswordLabel = new JLabel("Old Password:");
        JTextField oldPassword = new JTextField();
        oldPassword.setPreferredSize(new Dimension(200, 30));
        JLabel newPasswordLabel = new JLabel("New Password:");
        JTextField newPassword = new JTextField();
        newPassword.setPreferredSize(new Dimension(200, 30));
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String oldPasswordValue = oldPassword.getText();
                String newPasswordValue = newPassword.getText();
                // Update password in database
                String url = "jdbc:mysql://localhost:3306/mydb";
                String username = "root";
                String password = "Web#11*03";
                try (Connection conn = DriverManager.getConnection(url, username, password);
                     Statement stmt = conn.createStatement()) {
                    String query = "UPDATE student SET password = '" + newPasswordValue + "' WHERE password = '" + oldPasswordValue + "'";
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(mainPanel, "Password changed successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Error: " + ex.getMessage());
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "student");
            }
        });
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(oldPasswordLabel, gbc);
        gbc.gridx++;
        formPanel.add(oldPassword, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(newPasswordLabel, gbc);
        gbc.gridx++;
        formPanel.add(newPassword, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(backButton, gbc);
        gbc.gridx++;
        gbc.gridwidth = 2;
        formPanel.add(submitButton, gbc);
        add(formPanel, BorderLayout.CENTER);
    }
}
