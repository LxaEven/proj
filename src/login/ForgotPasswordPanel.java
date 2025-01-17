package login;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import main.*;
import java.awt.*;

public class ForgotPasswordPanel extends JPanel {
    public ForgotPasswordPanel(MainPanel mainPanel) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230));

        JLabel instructionLabel = new JLabel("Enter your email to reset your password", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionLabel.setForeground(Color.BLUE);

        JTextField emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Email", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(Color.RED);
        JButton sendCodeButton = new JButton("Send Code");
        sendCodeButton.setPreferredSize(new Dimension(200, 50));
        sendCodeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        sendCodeButton.setBackground(new Color(144, 238, 144));

        buttonPanel.add(backButton);
        buttonPanel.add(sendCodeButton);

        add(instructionLabel, BorderLayout.NORTH);
        add(emailField, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener for the back button
        backButton.addActionListener(e -> mainPanel.showScreen("Login"));

        // Add action listener for the send code button
        sendCodeButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Email field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String verificationCode = MainPanel.generateVerificationCode();
                MainPanel.setVerificationCode(verificationCode);
                MainPanel.setUserEmailOrId(email); // Store the email or ID for later verification
                // Simulate sending the verification code by displaying it in a dialog box
                JOptionPane.showMessageDialog(mainPanel, "Verification code sent to " + email + ": " + verificationCode, "Info", JOptionPane.INFORMATION_MESSAGE);
                mainPanel.showScreen("Verification");
            }
        });
    }
}