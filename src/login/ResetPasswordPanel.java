package login;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import main.*;
import java.awt.*;
import java.sql.*;

public class ResetPasswordPanel extends JPanel {
    private MainPanel mainPanel;
    private JPasswordField newPasswordField, comfirmPasswordField;

    public ResetPasswordPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230));

        // Logo setup
        ImageIcon imageIcon = new ImageIcon("image\\logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        // Instruction label
        JLabel instructionLabel = new JLabel("Enter a new password", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionLabel.setForeground(Color.BLUE);

        // New password field
        newPasswordField = new JPasswordField();
        newPasswordField.setPreferredSize(new Dimension(500, 60));
        newPasswordField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "New Password", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 20)));
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));

        comfirmPasswordField = new JPasswordField();
        comfirmPasswordField.setPreferredSize(new Dimension(500, 60));
        comfirmPasswordField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Confirm Password", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 20)));
        comfirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Show/hide password checkbox
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBackground(new Color(173, 216, 230));
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                newPasswordField.setEchoChar((char) 0);
                comfirmPasswordField.setEchoChar((char) 0);
            } else {
                newPasswordField.setEchoChar('•');
                comfirmPasswordField.setEchoChar('•');
            }
        });

        // Buttons
        JButton backButton = createStyledButton("Back", new Color(255, 102, 102));
        JButton resetButton = createStyledButton("Reset Password", new Color(144, 238, 144));

        // Button panel
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(backButton, gbc);
        gbc.gridx++;
        buttonPanel.add(resetButton, gbc);

        // Input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(173, 216, 230));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(instructionLabel, gbc);
        gbc.gridy++;
        inputPanel.add(newPasswordField, gbc);
        gbc.gridy++;
        inputPanel.add(comfirmPasswordField, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(showPasswordCheckBox, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(buttonPanel, gbc);

        // Logo panel
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setBackground(Color.CYAN);
        logoPanel.setPreferredSize(new Dimension(200, 0));
        logoPanel.add(logoLabel);

        add(inputPanel, BorderLayout.CENTER);
        add(logoPanel, BorderLayout.WEST);

        // Back button action
        backButton.addActionListener(e -> mainPanel.showScreen("Verification"));

        // Reset password button action
        resetButton.addActionListener(e -> {
            String newPassword = new String(newPasswordField.getPassword()).trim();
            String confirmPassword = new String(comfirmPasswordField.getPassword()).trim();
            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Password cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }if(!newPassword.equals(confirmPassword)){
                JOptionPane.showMessageDialog(mainPanel, "Passwords do not match!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                if (updatePasswordInDatabase(newPassword)) {
                    JOptionPane.showMessageDialog(mainPanel, "Password reset successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    mainPanel.showScreen("Login");
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Failed to reset password. Please try again.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        return button;
    }

    private boolean updatePasswordInDatabase(String newPassword) {
        String query = "UPDATE student SET student_password = ? WHERE student_email = ? OR phone_number = ?";

        try (Connection conn = DBConnect.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newPassword);
            stmt.setString(2, MainPanel.getUserEmailOrId()); // Use the identifier from the login panel
            stmt.setString(3, MainPanel.getUserEmailOrId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Return true if the password was updated successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}