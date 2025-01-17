package login;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class ResetPasswordPanel extends JPanel {
    public ResetPasswordPanel(MainPanel mainPanel) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230));

        JLabel instructionLabel = new JLabel("Enter a new password", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionLabel.setForeground(Color.BLUE);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "New Password", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create a checkbox to show/hide password
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBackground(new Color(173, 216, 230));
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                newPasswordField.setEchoChar((char) 0); // Show password
            } else {
                newPasswordField.setEchoChar('•'); // Hide password
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(173, 216, 230));
        inputPanel.add(newPasswordField, BorderLayout.CENTER);
        inputPanel.add(showPasswordCheckBox, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(Color.RED);
        JButton resetButton = new JButton("Reset Password");
        resetButton.setPreferredSize(new Dimension(200, 50));
        resetButton.setFont(new Font("Arial", Font.PLAIN, 18));
        resetButton.setBackground(new Color(144, 238, 144));

        buttonPanel.add(backButton);
        buttonPanel.add(resetButton);

        add(instructionLabel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener for the back button
        backButton.addActionListener(e -> mainPanel.showScreen("Verification"));

        // Add action listener for the reset password button
        resetButton.addActionListener(e -> {
            String newPassword = new String(newPasswordField.getPassword()).trim();
            if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Password reset successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainPanel.showScreen("Login");
            }
        });
    }
}