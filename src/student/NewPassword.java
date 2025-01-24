package student;

import java.awt.*;
import java.sql.*;
import  java.awt.event.*;
import javax.swing.*;

import main.*;

public class NewPassword extends JPanel {
    private MainPanel mainPanel;
    GridBagConstraints gbc = new GridBagConstraints();
    public NewPassword(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        newPassword();
    }

    void newPassword(){
        JLabel oldPasswordLabel = new JLabel("Old Password:");
        oldPasswordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JPasswordField oldPasswordField = new JPasswordField();
        oldPasswordField.setPreferredSize(new Dimension(200, 30));

        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setPreferredSize(new Dimension(200, 30));

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(200, 30));

        // Submit button setup
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.setFont(new Font("Arial", Font.BOLD, 13));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String oldPassword = new String(oldPasswordField.getPassword());
                String newPassword = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!newPassword.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(mainPanel, "New passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DBConnect.getConnection();
                     PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM student WHERE (email = ? OR phone_number = ?) AND student_password = ?");
                     PreparedStatement updateStmt = conn.prepareStatement("UPDATE student SET student_password = ? WHERE (email = ? OR phone_number = ?)")) {

                    String identifier = MainPanel.loginUserIdentifier;
                    checkStmt.setString(1, identifier);
                    checkStmt.setString(2, identifier);
                    checkStmt.setString(3, oldPassword);
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next()) {
                        // Update password
                        updateStmt.setString(1, newPassword);
                        updateStmt.setString(2, identifier);
                        updateStmt.setString(3, identifier);
                        updateStmt.executeUpdate();

                        JOptionPane.showMessageDialog(mainPanel, "Password changed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Old password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel formPanel = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(oldPasswordLabel, gbc);
        gbc.gridx++;
        formPanel.add(oldPasswordField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(newPasswordLabel, gbc);
        gbc.gridx++;
        formPanel.add(newPasswordField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx++;
        formPanel.add(confirmPasswordField, gbc);
        gbc.gridy++;
        formPanel.add(submitButton, gbc);
        add(formPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}
