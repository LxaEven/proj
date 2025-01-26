package main;

import javax.swing.*;
import student.*;
import login.*;

import java.awt.*;
import java.util.Random;
public class MainPanel extends JPanel {
    public static String loginUserIdentifier;
    public static String loginUserPassword;
    private CardLayout cardLayout;
    private static String verificationCode;
    private static String userEmailOrPhoneNumber;

    public MainPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        add(new startPanel(this), "start");
        add(new loginScreen(this), "loginScreen");
        add(new student(this), "student");
        
        add(new LoginPanel(this), "Login");
        add(new AdminLogin(this), "AdminLogin");
        add(new ForgotPasswordPanel(this), "ForgotPassword");
        add(new VerificationPanel(this), "Verification");
        add(new ResetPasswordPanel(this), "ResetPassword");
        // Remove the package declaration at the top of the file

        // Show the login screen by default
        showScreen("start");
    }

    public void showScreen(String screenName) {
        // Show the specified screen in the card layout
        cardLayout.show(this, screenName);
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