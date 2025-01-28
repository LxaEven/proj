package login;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class VerificationPanel extends JPanel {
    public VerificationPanel(MainPanel mainPanel) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230));

        ImageIcon imageIcon = new ImageIcon("image\\logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        JLabel instructionLabel = new JLabel("Enter the verification code sent to your email", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionLabel.setForeground(Color.BLUE);

        JTextField codeField = new JTextField();
        codeField.setPreferredSize(new Dimension(500, 60));
        codeField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Verification Code", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        codeField.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(new Color(255, 102, 102));
        JButton verifyButton = new JButton("Verify");
        verifyButton.setPreferredSize(new Dimension(200, 50));
        verifyButton.setFont(new Font("Arial", Font.PLAIN, 18));
        verifyButton.setBackground(new Color(144, 238, 144));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            buttonPanel.add(backButton, gbc);
            gbc.gridx++;
            buttonPanel.add(verifyButton, gbc);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(173, 216, 230));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(instructionLabel, gbc);
        gbc.gridy++;
        inputPanel.add(codeField, gbc);
        gbc.gridy++;
        inputPanel.add(buttonPanel, gbc);

        JPanel LogoPanel = new JPanel(new GridBagLayout());
        LogoPanel.setBackground(Color.CYAN);
        LogoPanel.setPreferredSize(new Dimension(200, 0));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        LogoPanel.add(logoLabel, gbc);

        add(inputPanel, BorderLayout.CENTER);
        add(LogoPanel, BorderLayout.WEST);

        // Add action listener for the back button
        backButton.addActionListener(e -> mainPanel.showScreen("ForgotPassword"));

        // Add action listener for the verify button
        verifyButton.addActionListener(e -> {
            String code = codeField.getText().trim();
            if (code.equals(MainPanel.getVerificationCode())) {
                JOptionPane.showMessageDialog(mainPanel, "Verification successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainPanel.showScreen("ResetPassword");
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Incorrect verification code!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}