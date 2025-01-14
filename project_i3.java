import javax.swing.*;
import java.awt.CardLayout;

public class project_i3 extends JFrame {

    public static void main(String[] args) {
        

        JFrame frame = new JFrame("Student management system");
        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new CardLayout());
        loginScreen loginScreenPanel = new loginScreen(mainPanel);
        startPanel startPanel = new startPanel(mainPanel);
        student studentPanel = new student(mainPanel);
        ViewScore ViewScorePanel = new ViewScore(mainPanel);
        ChangePassword ChangePasswordPanel = new ChangePassword(mainPanel);
        viewProfile ViewProfile = new viewProfile(mainPanel);

        mainPanel.add(startPanel, "start");
        mainPanel.add(loginScreenPanel, "loginScreen");
        mainPanel.add(studentPanel, "student");
        mainPanel.add(ViewScorePanel, "ViewScore");
        mainPanel.add(ChangePasswordPanel, "ChangePassword");
        mainPanel.add(ViewProfile, "ViewProfile");

        frame.add(mainPanel);
        frame.setVisible(true);
        

    }
} 