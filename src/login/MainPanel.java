package login;

import javax.swing.*;

import com.formdev.flatlaf.*;

import java.awt.*;
import java.util.Random;

public class MainPanel extends JFrame {
    public static String loginUserIdentifier;
    public static String loginUserPassword;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private static String verificationCode;
    private static String userEmailOrPhoneNumber;

    public MainPanel() {
        // Set JFrame properties
        setTitle("Login System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Initialize CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Add panels to the main panel
        mainPanel.add(new loginScreen(this), "loginScreen");
        mainPanel.add(new LoginPanel(this), "Login");
        mainPanel.add(new AdminLogin(this), "AdminLogin");
        mainPanel.add(new ForgotPasswordPanel(this), "ForgotPassword");
        mainPanel.add(new VerificationPanel(this), "Verification");
        mainPanel.add(new ResetPasswordPanel(this), "ResetPassword");

        add(mainPanel);

        showScreen("loginScreen");
    }

    public void showScreen(String screenName) {
        
        cardLayout.show(mainPanel, screenName);
    }

    public static String generateVerificationCode() {
        // Generate a 6-digit random verification code
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }

    public static void setVerificationCode(String code) {
        verificationCode = code;
    }

    public static String getVerificationCode() {
        return verificationCode;
    }

    public static void setUserEmailOrId(String emailOrPhoneNumber) {
        userEmailOrPhoneNumber = emailOrPhoneNumber;
    }

    public static String getUserEmailOrId() {
        return userEmailOrPhoneNumber;
    }


}
