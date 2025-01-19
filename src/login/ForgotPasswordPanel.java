package login;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import main.*;
import java.awt.*;

public class ForgotPasswordPanel extends JPanel {
    public ForgotPasswordPanel(MainPanel mainPanel) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230));

        ImageIcon imageIcon = new ImageIcon("image\\logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        JLabel instructionLabel = new JLabel("Enter your email to reset your password", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionLabel.setForeground(Color.BLUE);

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(500, 60));
        emailField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Email", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(new Color(255, 102, 102));
        JButton sendCodeButton = new JButton("Send Code");
        sendCodeButton.setPreferredSize(new Dimension(200, 50));
        sendCodeButton.setFont(new Font("Arial", Font.PLAIN, 18));
        sendCodeButton.setBackground(new Color(144, 238, 144));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            buttonPanel.add(backButton, gbc);
            gbc.gridx++;
            buttonPanel.add(sendCodeButton, gbc);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(173, 216, 230));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(instructionLabel, gbc);
        gbc.gridy++;
        inputPanel.add(emailField, gbc);
        gbc.gridy++;
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