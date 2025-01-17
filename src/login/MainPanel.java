package login;
import javax.swing.*;

import student.*;

import java.awt.*;
import java.util.Random;
public class MainPanel extends JPanel {
    private CardLayout cardLayout;
    private static String verificationCode;
    private static String userEmailOrId;

    public MainPanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Add different screens to the main panel
        add(new startPanel(this), "start");
        add(new loginScreen(this), "loginScreen");
        add(new student(this), "student");
        add(new ViewScore(this), "ViewScore");
        add(new ChangePassword(this), "ChangePassword");
        add(new viewProfile(this), "ViewProfile");
        add(new ViewCourse(this), "ViewCourse");
        add(new student(this), "student");
        add(new LoginPanel(this), "Login");
        add(new ForgotPasswordPanel(this), "ForgotPassword");
        add(new VerificationPanel(this), "Verification");
        add(new ResetPasswordPanel(this), "ResetPassword");
        

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

    public static void setUserEmailOrId(String emailOrId) {
        userEmailOrId = emailOrId;
    }

    public static String getUserEmailOrId() {
        return userEmailOrId;
    }
}