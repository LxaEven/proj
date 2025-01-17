package login;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class VerificationPanel extends JPanel {
    public VerificationPanel(MainPanel mainPanel) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230));

        JLabel instructionLabel = new JLabel("Enter the verification code sent to your email", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionLabel.setForeground(Color.BLUE);

        JTextField codeField = new JTextField();
        codeField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Verification Code", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        codeField.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(Color.RED);
        JButton verifyButton = new JButton("Verify");
        verifyButton.setPreferredSize(new Dimension(200, 50));
        verifyButton.setFont(new Font("Arial", Font.PLAIN, 18));
        verifyButton.setBackground(new Color(144, 238, 144));

        buttonPanel.add(backButton);
        buttonPanel.add(verifyButton);

        add(instructionLabel, BorderLayout.NORTH);
        add(codeField, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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