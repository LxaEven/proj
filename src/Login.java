import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Login {
    private static String verificationCode;


    static void showLoginScreen() {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        frame.add(panel, BorderLayout.CENTER);

        JTextField usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("ID or Email"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Log In");
        JButton forgotPasswordButton = new JButton("Forgot Password?");
        buttonPanel.add(loginButton);
        buttonPanel.add(forgotPasswordButton);

        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(buttonPanel);

        // Dummy login credentials
        String validUsername = "e20220305";
        String validPassword = "1234";

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your email.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (username.equals(validUsername) && password.equals(validPassword)) {
                JOptionPane.showMessageDialog(frame, "Login successful!", "Info", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                loginSuccess = true;
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        forgotPasswordButton.addActionListener(e -> {
            frame.dispose();
            showAccountSearchScreen();
        });

        frame.setVisible(true);
    }

    private static void showAccountSearchScreen() {
        JFrame frame = new JFrame("Find Your Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        frame.add(panel, BorderLayout.CENTER);

        JLabel instructionLabel = new JLabel("Enter your email to search for your account:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(instructionLabel);

        JTextField inputField = new JTextField();
        panel.add(inputField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton cancelButton = new JButton("Cancel");
        JButton searchButton = new JButton("Search");
        buttonPanel.add(cancelButton);
        buttonPanel.add(searchButton);
        panel.add(buttonPanel);

        cancelButton.addActionListener(e -> {
            frame.dispose();
            showLoginScreen();
        });

        searchButton.addActionListener(e -> {
            String userInput = inputField.getText();
            if (userInput.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter an email.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Searching for: " + userInput, "Info", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                showVerificationScreen(userInput);
            }
        });

        frame.setVisible(true);
    }

    private static void showVerificationScreen(String userInput) {
        JFrame frame = new JFrame("Verify Code");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 1, 10, 10));


        JTextField codeField = new JTextField();
        codeField.setBorder(BorderFactory.createTitledBorder("Enter Verification Code"));

        JButton verifyButton = new JButton("Continue");

        JLabel messageLabel = new JLabel(" ", SwingConstants.CENTER);

        frame.add(codeField);
        frame.add(verifyButton);
        frame.add(messageLabel);

        verificationCode = sendVerificationCode(userInput);

        verifyButton.addActionListener(e -> {
            String inputCode = codeField.getText();
            if (inputCode.equals(verificationCode)) {
                frame.dispose();
                showResetPasswordScreen();
            } else {
                messageLabel.setText("Incorrect code. Try again.");
                messageLabel.setForeground(Color.RED);
            }
        });

        frame.setVisible(true);
    }

    private static void showResetPasswordScreen() {
        JFrame frame = new JFrame("Reset Password");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 1, 10, 10));

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBorder(BorderFactory.createTitledBorder("Enter New Password"));

        JButton resetButton = new JButton("Continue");

        JLabel messageLabel = new JLabel(" ", SwingConstants.CENTER);

        frame.add(newPasswordField);
        frame.add(resetButton);
        frame.add(messageLabel);

        resetButton.addActionListener(e -> {
            String newPassword = new String(newPasswordField.getPassword());
            if (!newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Password reset successfully!");
                frame.dispose();
                showLoginScreen();
            } else {
                messageLabel.setText("Password cannot be empty.");
                messageLabel.setForeground(Color.RED);
            }
        });

        frame.setVisible(true);
    }

    private static String sendVerificationCode(String userInput) {
        Random random = new Random();
        String code = String.format("%06d", random.nextInt(999999));
        JOptionPane.showMessageDialog(null, "Verification code sent to " + userInput + ": " + code);
        return code;
    }

    public static boolean loginSuccess = false;
}
