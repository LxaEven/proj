package login;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import main.*;
import java.awt.*;
public class LoginPanel extends JPanel {
    public LoginPanel(MainPanel mainPanel) {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(173, 216, 230)); // Light Blue background

        ImageIcon imageIcon = new ImageIcon("image\\logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);


        // Create the input panel for email and password
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(173, 216, 230));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(500, 60));
        emailField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Phone Number or Email", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.PLAIN, 20)));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(500, 60));
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



        JButton loginButton = new JButton("Log In");
        loginButton.setPreferredSize(new Dimension(200, 50));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 18));
        loginButton.setBackground(new Color(144, 238, 144)); // Light Green
        JButton forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.setPreferredSize(new Dimension(200, 50));
        forgotPasswordButton.setFont(new Font("Arial", Font.PLAIN, 18));
        forgotPasswordButton.setBackground(new Color(255, 200, 100)); // Orange
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));
        backButton.setBackground(new Color(255, 102, 102));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets= new Insets(10,10,10,10);
            JPanel buttonPanel = new JPanel(new GridBagLayout());
            buttonPanel.setBackground(new Color(173, 216, 230));
            gbc.gridx = 0;
            gbc.gridy = 0;
            buttonPanel.add(loginButton, gbc);
            gbc.gridx++;
            buttonPanel.add(forgotPasswordButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(emailField, gbc);
        gbc.gridy++;
        inputPanel.add(passwordField, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(showPasswordCheckBox, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(buttonPanel, gbc);
        gbc.gridy++;
        inputPanel.add(backButton, gbc);


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

        // Add action listener for the login button
        backButton.addActionListener(e -> mainPanel.showScreen("loginScreen"));
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