import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class Main2 extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String verificationCode;
    private HashMap<String, String> userDatabase = new HashMap<>();

    public Main2() {
        initializeDatabase();
        setupMainUI();
    }

    private void initializeDatabase() {
        // Initialize the user database with some sample users
        userDatabase.put("user", "123");
        userDatabase.put("admin@example.com", "adminPass");
    }

    private void setupMainUI() {
        // Set up the card layout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add different screens to the main panel
        mainPanel.add(createLoginScreen(), "Login");
        mainPanel.add(createForgotPasswordScreen(), "ForgotPassword");
        mainPanel.add(createVerificationScreen(), "Verification");
        mainPanel.add(createResetPasswordScreen(), "ResetPassword");

        mainPanel.add(createLoginScreen(), "Student");
        // Add the main panel to this JPanel
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);

        // Show the login screen by default
        showScreen("Login");
    }

    private JPanel createLoginScreen() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(173, 216, 230)); // Light Blue background

        JLabel titleLabel = new JLabel("Welcome to Secure Login System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLUE);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 20, 20));
        inputPanel.setBackground(new Color(173, 216, 230));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField emailField = new JTextField();
        emailField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Phone Number or Email", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Password", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));

        inputPanel.add(emailField);
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(173, 216, 230));

        JButton loginButton = new JButton("Log In");
        loginButton.setPreferredSize(new Dimension(200, 50));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBackground(new Color(144, 238, 144));
        JButton forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.setPreferredSize(new Dimension(200, 50));
        forgotPasswordButton.setFont(new Font("Arial", Font.PLAIN, 18));
        forgotPasswordButton.setBackground(new Color(255, 165, 0));

        buttonPanel.add(loginButton);
        buttonPanel.add(forgotPasswordButton);

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (userDatabase.containsKey(email) && userDatabase.get(email).equals(password)) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);  
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        forgotPasswordButton.addActionListener(e -> showScreen("ForgotPassword"));

        return panel;
    }

    private JPanel createForgotPasswordScreen() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(173, 216, 230));

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
        JButton verifyButton = new JButton("Send Code");
        verifyButton.setPreferredSize(new Dimension(200, 50));
        verifyButton.setFont(new Font("Arial", Font.PLAIN, 18));
        verifyButton.setBackground(new Color(144, 238, 144));

        buttonPanel.add(backButton);
        buttonPanel.add(verifyButton);

        panel.add(instructionLabel, BorderLayout.NORTH);
        panel.add(emailField, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> showScreen("ForgotPassword"));

        verifyButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            if (email.equals(verificationCode)) {
                JOptionPane.showMessageDialog(this, "Verification successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                showScreen("ResetPassword");
            } else if (userDatabase.containsKey(email)) {
                verificationCode = generateVerificationCode();
                JOptionPane.showMessageDialog(this, "Verification code sent to " + email + ": " + verificationCode, "Info", JOptionPane.INFORMATION_MESSAGE);
                showScreen("Verification");
            } else {
                JOptionPane.showMessageDialog(this, "Email not found in our records!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createVerificationScreen() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(173, 216, 230));


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

        panel.add(instructionLabel, BorderLayout.NORTH);
        panel.add(codeField, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener for the back button
        backButton.addActionListener(e -> showScreen("ForgotPassword"));

        // Add action listener for the verify button
        verifyButton.addActionListener(e -> {
            String code = codeField.getText().trim();
            if (code.equals(verificationCode)) {
                JOptionPane.showMessageDialog(this, "Verification successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                showScreen("ResetPassword");
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect verification code!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        return new JPanel(); // Placeholder
    }

    private JPanel createResetPasswordScreen() {
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        panel.setBackground(new Color(173, 216, 230));

        JLabel instructionLabel = new JLabel("Enter a new password", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionLabel.setForeground(Color.BLUE);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "New Password", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));

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

        panel.add(instructionLabel, BorderLayout.NORTH);
        panel.add(newPasswordField, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener for the back button
        backButton.addActionListener(e -> showScreen("Verification"));


        // Add action listener for the reset password button
        resetButton.addActionListener(e -> {
            String newPassword = new String(newPasswordField.getPassword()).trim();
            if (newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Password reset successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                showScreen("Login");
            }
        });
        return new JPanel(); // Placeholder
    }

    private String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    private void showScreen(String screenName) {
        cardLayout.show(mainPanel, "screenName");
    }
}
