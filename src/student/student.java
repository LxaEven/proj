package student;
import javax.swing.*;

import login.LoginPanel;
import main.MainPanel;

import java.awt.*;
import java.awt.event.*;

public class student extends JPanel {

    private displayProfile currentProfilePanel; 
    private DisplayScore studentScorePanel;
    private displayCourse studentCoursePanel;
    private NewPassword ChangeNewPassword;
    public JButton ViewProfile, ViewScore, ViewCourse, ChangePassword;
    
    public student(MainPanel mainPanel){
        setLayout(new BorderLayout());
        setBackground(new Color(173, 216, 230));

        ImageIcon imageIcon = new ImageIcon("image//logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);


        ViewProfile = new JButton("View Profile");
        ViewProfile.setFont(new Font("Arial", Font.BOLD, 15));
        ViewProfile.setPreferredSize(new Dimension(200, 40));
        ViewProfile.setFocusPainted(false);
        ViewProfile.addActionListener(e -> updateProfilePanel(mainPanel));


        ViewScore = new JButton("View Score");
        ViewScore.setFont(new Font("Arial", Font.BOLD, 15));
        ViewScore.setPreferredSize(new Dimension(200, 40));
        ViewScore.setFocusPainted(false);
        ViewScore.addActionListener(e->showStudentScorePanel(mainPanel));


        ViewCourse = new JButton("View Course");
        ViewCourse.setFont(new Font("Arial", Font.BOLD, 15));
        ViewCourse.setPreferredSize(new Dimension(200, 40));
        ViewCourse.setFocusPainted(false);
        ViewCourse.addActionListener(e -> showStudentCoursePanel(mainPanel));


        ChangePassword = new JButton("Change Password");
        ChangePassword.setFont(new Font("Arial", Font.BOLD, 15));
        ChangePassword.setPreferredSize(new Dimension(200, 40));
        ChangePassword.setFocusPainted(false);
        ChangePassword.addActionListener(e -> ChangePassword(mainPanel));

        JButton[] buttons = {ViewProfile, ViewScore, ViewCourse, ChangePassword};
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton button : buttons) {
                    button.setBackground(Color.WHITE);
                    button.setForeground(Color.BLACK);
                }
                ((JButton) e.getSource()).setBackground(new Color(173, 216, 230));
                ((JButton) e.getSource()).setForeground(new Color(82, 39, 25));
            }
        };

        for (JButton button : buttons) {
            button.addActionListener(buttonListener);
        }


        JButton Logout = new JButton("Logout");
        Logout.setFont(new Font("Arial", Font.BOLD, 15));
        Logout.setPreferredSize(new Dimension(200, 40));
        Logout.setFocusPainted(false);
        Logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    null, 
                    "Do you want to log out?", 
                    "Log Out", 
                    JOptionPane.YES_NO_OPTION
                );
                
                if (response == JOptionPane.YES_OPTION) {
                    clearMainContent();
                    clearColor();
                    mainPanel.showScreen("loginScreen");
                } else {
                    System.out.println("Stayed logged in");
                }
            }
        });


        JButton CloseProgram = new JButton("Exit");
        CloseProgram.setFont(new Font("Arial", Font.BOLD, 15));
        CloseProgram.setPreferredSize(new Dimension(200, 40));
        CloseProgram.setFocusPainted(false);
        CloseProgram.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    null, 
                    "Do you want to Exit the program?", 
                    "Exit the program", 
                    JOptionPane.YES_NO_OPTION
                );
                
                if (response == JOptionPane.NO_OPTION) {
                    CardLayout c4 = (CardLayout) mainPanel.getLayout();
                    c4.show(mainPanel, "student");
                } else {
                    System.out.println("Program ended");
                    System.exit(0);
                }
            }
        });


        


        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.CYAN);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.add(logoLabel);
        logoPanel.setBackground(Color.CYAN);

        buttonPanel.add(logoPanel, BorderLayout.NORTH);

        JPanel buttonsContainer = new JPanel(new GridBagLayout());
        buttonsContainer.setPreferredSize(new Dimension(250, 500));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;

        buttonsContainer.add(ViewProfile, gbc);
        gbc.gridy++;
        buttonsContainer.add(ViewScore, gbc);
        gbc.gridy++;
        buttonsContainer.add(ViewCourse, gbc);
        gbc.gridy++;
        buttonsContainer.add(ChangePassword, gbc);

        buttonsContainer.setBackground(Color.CYAN);
        buttonPanel.add(buttonsContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.WEST);

        JPanel logoutAndClosePanel = new JPanel(new GridBagLayout());
        logoutAndClosePanel.setBackground(Color.CYAN);
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        logoutAndClosePanel.add(Logout, gbc);
        gbc.gridx++;
        logoutAndClosePanel.add(CloseProgram, gbc);
        add(logoutAndClosePanel, BorderLayout.SOUTH);
    
    }

    private void updateProfilePanel(MainPanel mainPanel) {
        clearMainContent(); 
        currentProfilePanel = new displayProfile(mainPanel); 
        add(currentProfilePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    private void showStudentScorePanel(MainPanel mainPanel) {
        clearMainContent();
        studentScorePanel = new DisplayScore(mainPanel);
        add(studentScorePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showStudentCoursePanel(MainPanel mainPanel){
        clearMainContent();
        studentCoursePanel = new displayCourse(mainPanel);
        add(studentCoursePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void ChangePassword(MainPanel mainPanel) {
        clearMainContent();
        ChangeNewPassword = new NewPassword(mainPanel);
        add(ChangeNewPassword, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    public void clearColor() {
        JButton[] buttons = {ViewProfile, ViewScore, ViewCourse, ChangePassword};
        for (JButton button : buttons) {
            button.setBackground(Color.WHITE);
        }
    }
    
    public void clearMainContent() {
        if (currentProfilePanel != null) {
            remove(currentProfilePanel);
            currentProfilePanel = null;
        }
        if (studentScorePanel != null) {
            remove(studentScorePanel);
            studentScorePanel = null;
        }
        if (studentCoursePanel != null) {
            remove(studentCoursePanel);
            studentCoursePanel = null;
        }
        if (ChangeNewPassword != null) {
            remove(ChangeNewPassword);
            ChangeNewPassword = null;
        }
    }

}
