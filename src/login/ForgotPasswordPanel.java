package login;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import main.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPasswordPanel extends JPanel {

    public ForgotPasswordPanel(MainPanel mainPanel) {
        configurePanelLayout();

        // Create components
        JLabel logoLabel = createLogoLabel("image\\logo.jpg", 300, 300);
        JLabel instructionLabel = createInstructionLabel();
        JTextField emailField = createEmailField();
        JPanel buttonPanel = createButtonPanel(mainPanel, emailField);

        // Assemble input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(instructionLabel, gbc);
        gbc.gridy++;
        inputPanel.add(emailField, gbc);
        gbc.gridy++;
        inputPanel.add(buttonPanel, gbc);

        // Assemble logo panel
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setBackground(Color.CYAN);
        logoPanel.setPreferredSize(new Dimension(400, 200));
        logoPanel.add(logoLabel, gbc);

        // Add panels to the main layout
        add(inputPanel, BorderLayout.CENTER);
        add(logoPanel, BorderLayout.WEST);
    }

    private void configurePanelLayout() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230));
    }

    private JLabel createLogoLabel(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image resizedImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(resizedImage));
    }

    private JLabel createInstructionLabel() {
        JLabel label = new JLabel("Enter your email to reset your password", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.BLUE);
        return label;
    }

    private JTextField createEmailField() {
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(500, 60));
        emailField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Email", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 20)));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        return emailField;
    }

    private JPanel createButtonPanel(MainPanel mainPanel, JTextField emailField) {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton backButton = createButton("Back", new Color(255, 102, 102), e -> mainPanel.showScreen("Login"));
        JButton sendCodeButton = createButton("Send Code", new Color(144, 238, 144),
                e -> handleSendCode(mainPanel, emailField));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        buttonPanel.add(backButton, gbc);
        gbc.gridx++;
        buttonPanel.add(sendCodeButton, gbc);

        return buttonPanel;
    }

    private JButton createButton(String text, Color backgroundColor, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(backgroundColor);
        button.addActionListener(actionListener);
        return button;
    }

    private void handleSendCode(MainPanel mainPanel, JTextField emailField) {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "Email field cannot be empty!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isEmailRegistered(email)) {
            JOptionPane.showMessageDialog(mainPanel, "Email is not registered!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String verificationCode = MainPanel.generateVerificationCode();
        MainPanel.setVerificationCode(verificationCode);
        MainPanel.setUserEmailOrId(email);

        JOptionPane.showMessageDialog(mainPanel, "Verification code sent to " + email + ": " + verificationCode,
                "Info", JOptionPane.INFORMATION_MESSAGE);
        mainPanel.showScreen("Verification");
    }

    private boolean isEmailRegistered(String email) {
        String query = "SELECT 1 FROM student WHERE email = ?";

        try (Connection conn = DBConnectionEmail.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
