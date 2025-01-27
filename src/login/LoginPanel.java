package login;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import main.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPanel extends JPanel {
    private static String identifier;
    private static String password;

    private JTextField emailField;
    private JPasswordField passwordField;
    private MainPanel mainPanel;

    public LoginPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(135, 206, 235)); // Sky Blue background

        // Logo setup
        ImageIcon imageIcon = new ImageIcon("image\\logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        // Create the input panel for email and password
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(176, 224, 230)); // Light Steel Blue
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(500, 60));
        emailField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Email or Phone Number", TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 20)));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(500, 60));
        passwordField.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Password", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.PLAIN, 20)));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Checkbox to show/hide password
        JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBackground(new Color(176, 224, 230));
        showPasswordCheckBox.setFont(new Font("Arial", Font.PLAIN, 18));
        showPasswordCheckBox.addActionListener(e -> {
            passwordField.setEchoChar(showPasswordCheckBox.isSelected() ? (char) 0 : '•');
        });

        // Buttons
        JButton loginButton = createStyledButton("Log In", new Color(144, 238, 144)); // Medium Sea Green
        JButton forgotPasswordButton = createStyledButton("Forgot Password?", new Color(255, 160, 122)); // Light Salmon
        JButton backButton = createStyledButton("Back", new Color(255, 102, 102)); // Light Coral

        // Add components to the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

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

        // Logo panel
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setBackground(new Color(135, 206, 250)); // Light Sky Blue
        logoPanel.setPreferredSize(new Dimension(400, 200));
        logoPanel.add(logoLabel);

        add(inputPanel, BorderLayout.CENTER);
        add(logoPanel, BorderLayout.WEST);

        // Action Listeners
        backButton.addActionListener(e -> mainPanel.showScreen("loginScreen"));
        loginButton.addActionListener(new LoginButtonListener());
        forgotPasswordButton.addActionListener(e -> mainPanel.showScreen("ForgotPassword"));
    }

    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        return button;
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            identifier = emailField.getText().trim();
            password = new String(passwordField.getPassword()).trim();
            MainPanel.loginUserIdentifier = identifier;
            MainPanel.loginUserPassword = password;

            if (identifier.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Please enter both Email/Phone Number and password.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (authenticateUser(identifier, password)) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Login successfully");
                mainPanel.showScreen("student");
            } else {
                JOptionPane.showMessageDialog(LoginPanel.this, "Invalid Email/Phone Number or password.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean authenticateUser(String identifier, String password) {
        String query = "SELECT * FROM student WHERE (email = ? OR phone_number = ?) AND student_password = ?";

        try (Connection conn = DBConnect.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, identifier);
            stmt.setString(2, identifier);
            stmt.setString(3, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void clearFields() {
        emailField.setText("");
        passwordField.setText("");
    }

}
