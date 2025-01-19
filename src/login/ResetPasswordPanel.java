package login;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import main.*;
import java.awt.*;

public class ResetPasswordPanel extends JPanel {
    public ResetPasswordPanel(MainPanel mainPanel) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230));

        ImageIcon imageIcon = new ImageIcon("image\\logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        JLabel instructionLabel = new JLabel("Enter a new password", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionLabel.setForeground(Color.BLUE);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setPreferredSize(new Dimension(500, 60));
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

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(new Color(255, 102, 102));
        JButton resetButton = new JButton("Reset Password");
        resetButton.setPreferredSize(new Dimension(200, 50));
        resetButton.setFont(new Font("Arial", Font.PLAIN, 18));
        resetButton.setBackground(new Color(144, 238, 144));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            buttonPanel.add(backButton, gbc);
            gbc.gridx++;
            buttonPanel.add(resetButton, gbc);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(173, 216, 230));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(instructionLabel, gbc);
        gbc.gridy++;
        inputPanel.add(newPasswordField, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(showPasswordCheckBox, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(buttonPanel, gbc);

        JPanel LogoPanel = new JPanel(new GridBagLayout());
        LogoPanel.setBackground(Color.CYAN);
        LogoPanel.setPreferredSize(new Dimension(400, 200));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        LogoPanel.add(logoLabel, gbc);

        add(inputPanel, BorderLayout.CENTER);
        add(LogoPanel, BorderLayout.WEST);

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