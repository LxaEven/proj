package login;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import main.*;
import java.awt.*;
public class LoginPanel extends JPanel {
    public LoginPanel(MainPanel mainPanel) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230)); // Light Blue background

        // Add a title label
        JLabel titleLabel = new JLabel("Welcome to Student Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLUE);
        add(titleLabel, BorderLayout.NORTH);

        // Create the input panel for email and password
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        inputPanel.setBackground(new Color(173, 216, 230));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JTextField emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "ID or Email", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Password", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create a checkbox to show/hide password
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBackground(new Color(173, 216, 230));
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('•'); // Hide password
            }
        });

        inputPanel.add(emailField);
        inputPanel.add(passwordField);
        inputPanel.add(showPasswordCheckBox);

        // Create the button panel for login and forgot password buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton loginButton = new JButton("Log In");
        loginButton.setPreferredSize(new Dimension(200, 50));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBackground(new Color(144, 238, 144)); // Light Green
        JButton forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.setPreferredSize(new Dimension(200, 50));
        forgotPasswordButton.setFont(new Font("Arial", Font.PLAIN, 18));
        forgotPasswordButton.setBackground(new Color(255, 165, 0)); // Orange

        buttonPanel.add(loginButton);
        buttonPanel.add(forgotPasswordButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener for the login button
        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Simulate a successful login for demonstration purposes
                JOptionPane.showMessageDialog(mainPanel, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                mainPanel.showScreen("student");
            }
        });

        // Add action listener for the forgot password button
        forgotPasswordButton.addActionListener(e -> mainPanel.showScreen("ForgotPassword"));
    }
}