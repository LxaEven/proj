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

    private MainPanel mainPanel;
    private JLabel logoLabel = createLogoLabel("image/logo.jpg", 300, 300); // Portable image path
    private JLabel instructionLabel = createInstructionLabel();
    private JTextField emailField = createEmailField();
    private JPanel buttonPanel;

    public ForgotPasswordPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel; // Ensure mainPanel is properly assigned
        configurePanelLayout();

        // Create components
        buttonPanel = createButtonPanel();

        // Assemble input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(173, 216, 230));
        GridBagConstraints inputGbc = new GridBagConstraints();
        inputGbc.insets = new Insets(10, 10, 10, 10);
        inputGbc.gridx = 0;
        inputGbc.gridy = 0;
        inputPanel.add(instructionLabel, inputGbc);
        inputGbc.gridy++;
        inputPanel.add(emailField, inputGbc);
        inputGbc.gridy++;
        inputPanel.add(buttonPanel, inputGbc);

        // Assemble logo panel
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setBackground(Color.CYAN);
        logoPanel.setPreferredSize(new Dimension(400, 200));
        GridBagConstraints logoGbc = new GridBagConstraints();
        logoGbc.gridx = 0;
        logoGbc.gridy = 0;
        logoPanel.add(logoLabel, logoGbc);

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
        JLabel label = new JLabel("Enter your email or phone number to reset your password", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setForeground(Color.BLUE);
        return label;
    }

    private JTextField createEmailField() {
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(500, 60));
        emailField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Email or Phone Number", TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 20)));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        return emailField;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton backButton = createButton("Back", new Color(255, 102, 102), e -> mainPanel.showScreen("Login"));
        JButton sendCodeButton = createButton("Send Code", new Color(144, 238, 144),
                e -> handleSendCode());

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

    private void handleSendCode() {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            showMessage("Email field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isEmailRegistered(email)) {
            showMessage("Email not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String verificationCode = MainPanel.generateVerificationCode();
        MainPanel.setVerificationCode(verificationCode);
        MainPanel.setUserEmailOrId(email);

        showMessage("Verification code sent to " + email + ": " + verificationCode, "Info",
                JOptionPane.INFORMATION_MESSAGE);
        mainPanel.showScreen("Verification");
    }

    private boolean isEmailRegistered(String email) {
        String query = "SELECT 1 FROM student WHERE email = ? OR phone_number = ?";

        try (Connection conn = DBConnectionEmail.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showMessage(String message, String title, int messageType) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(mainPanel, message, title, messageType));
    }
}
