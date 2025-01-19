package student;
import java.sql.*;
import javax.swing.*;
import com.formdev.flatlaf.*;

import main.DBConnect;
import main.MainPanel;

import java.awt.*;
import java.awt.event.*;

public class ChangePassword extends JPanel {
    public ChangePassword(MainPanel mainPanel) {
        setLayout(new BorderLayout());
        

        ImageIcon imageIcon = new ImageIcon("image//logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        
        JLabel oldPasswordLabel = new JLabel("Old Password:");
        oldPasswordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField oldPassword = new JTextField();
        oldPassword.setPreferredSize(new Dimension(200, 30));


        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(new Font("Arial", Font.BOLD, 13));
        JTextField newPassword = new JTextField();
        newPassword.setPreferredSize(new Dimension(200, 30));


        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.setFont(new Font("Arial", Font.BOLD, 13));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String oldPasswordValue = oldPassword.getText();
                String newPasswordValue = newPassword.getText();

                try (Connection conn = DBConnect.getConnection();
                     Statement stmt = conn.createStatement()) {
                    String query = "UPDATE student SET phone_number = '" + newPasswordValue + "' WHERE phone_number = '" + oldPasswordValue + "'";
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(mainPanel, "Password changed successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainPanel, "Error: " + ex.getMessage());
                }
            }
        });


        JButton ViewProfile = new JButton("View Profile");
        ViewProfile.setFont(new Font("Arial", Font.BOLD, 13));
        ViewProfile.setPreferredSize(new Dimension(160, 30));
        ViewProfile.setFocusPainted(false);
        ViewProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ViewProfile");
            }
        });


        JButton ViewScore = new JButton("View Score");
        ViewScore.setFont(new Font("Arial", Font.BOLD, 13));
        ViewScore.setPreferredSize(new Dimension(160, 30));
        ViewScore.setFocusPainted(false);
        ViewScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ViewScore");
            }
        });


        JButton ViewCourse = new JButton("View Course");
        ViewCourse.setFont(new Font("Arial", Font.BOLD, 13));
        ViewCourse.setPreferredSize(new Dimension(160, 30));
        ViewCourse.setFocusPainted(false);
        ViewCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ViewCourse");
            }
        });


        JButton ChangePassword = new JButton("Change Password");
        ChangePassword.setFont(new Font("Arial", Font.BOLD, 13));
        ChangePassword.setPreferredSize(new Dimension(160, 30));
        ChangePassword.setFocusPainted(false);
        ChangePassword.setBackground(Color.GRAY);
        ChangePassword.setForeground(Color.WHITE);
        ChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout c4 = (CardLayout) mainPanel.getLayout();
                c4.show(mainPanel, "ChangePassword");
            }
        });


        JButton Logout = new JButton("Logout");
        Logout.setFont(new Font("Arial", Font.BOLD, 13));
        Logout.setPreferredSize(new Dimension(160, 30));
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
                    CardLayout c4 = (CardLayout) mainPanel.getLayout();
                    c4.show(mainPanel, "loginScreen");
                } else {
                    System.out.println("Stayed logged in");
                }
            }
        });


        JButton CloseProgram = new JButton("Exit");
        CloseProgram.setFont(new Font("Arial", Font.BOLD, 13));
        CloseProgram.setPreferredSize(new Dimension(160, 30));
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
                    c4.show(mainPanel, "ChangePassword");
                } else {
                    System.out.println("Program ended");
                    System.exit(0);
                }
            }
        });

        JButton darkMode = new JButton("Dark Mode");
        darkMode.setFont(new Font("Arial", Font.BOLD, 12));
        darkMode.setPreferredSize(new Dimension(130, 30));
        darkMode.setFocusPainted(false);
        darkMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel( new FlatDarkLaf() );
                    SwingUtilities.updateComponentTreeUI(mainPanel);
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
            }
        });

        JButton lightMode = new JButton("Light Mode");
        lightMode.setFont(new Font("Arial", Font.BOLD, 12));
        lightMode.setPreferredSize(new Dimension(130, 30));
        lightMode.setFocusPainted(false);
        lightMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    UIManager.setLookAndFeel( new FlatLightLaf() );
                    SwingUtilities.updateComponentTreeUI(mainPanel);
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
            }
        });



        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.GRAY);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.add(logoLabel);
        logoPanel.setBackground(Color.GRAY);

        buttonPanel.add(logoPanel, BorderLayout.NORTH);

        JPanel buttonsContainer = new JPanel(new GridBagLayout());
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
        gbc.gridy++;
        buttonsContainer.add(Logout, gbc);
        gbc.gridy++;
        buttonsContainer.add(CloseProgram, gbc);

        buttonsContainer.setBackground(Color.GRAY);
        buttonPanel.add(buttonsContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.WEST);


        JPanel formPanel = new JPanel(new GridBagLayout());
        buttonPanel.setPreferredSize(new Dimension(200, 300));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(oldPasswordLabel, gbc);
        gbc.gridx++;
        formPanel.add(oldPassword, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(newPasswordLabel, gbc);
        gbc.gridx++;
        formPanel.add(newPassword, gbc);
        gbc.gridy++;
        formPanel.add(submitButton, gbc);
        add(formPanel, BorderLayout.CENTER);

        JPanel ModePanel = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(20, 20, 20, 20);
        ModePanel.setPreferredSize(new Dimension(180, 50));
        gbc.gridy = 0;
        gbc.gridx = 0;
        ModePanel.add(darkMode, gbc);
        gbc.gridx++;
        ModePanel.add(lightMode, gbc);
        ModePanel.setBackground(Color.GRAY);
        add(ModePanel, BorderLayout.NORTH);

        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(Color.GRAY);
        eastPanel.setPreferredSize(new Dimension(50, 300));
        add(eastPanel, BorderLayout.EAST);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.GRAY);
        southPanel.setPreferredSize(new Dimension(200, 50));
        add(southPanel, BorderLayout.SOUTH);
    }
}
