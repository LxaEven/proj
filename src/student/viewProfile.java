package student;
import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.*;

import main.MainPanel;

import java.awt.event.*;
import java.sql.*;

public class viewProfile extends JPanel{
    public viewProfile(MainPanel mainPanel){
        
        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "Web#11*03";
        setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("image//logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        
        JLabel idLabel = new JLabel("Student ID:   ");
        idLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel firstnameLabel = new JLabel("Firstname:   ");
        firstnameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel lastnameLabel = new JLabel("Lastname:  ");
        lastnameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel genderLabel = new JLabel("Gender:   ");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel birthLabel = new JLabel("Birth:   ");
        birthLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel phoneNumberLabel = new JLabel("Phone Number:   ");
        phoneNumberLabel.setFont(new Font("Arial", Font.BOLD, 17));
        JLabel emailLabel = new JLabel("Email:   ");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 17));

        GridBagConstraints gbc = new GridBagConstraints();
        JPanel profilePanel = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        profilePanel.add(idLabel, gbc);
        gbc.gridy++;
        profilePanel.add(firstnameLabel, gbc);
        gbc.gridy++;
        profilePanel.add(lastnameLabel, gbc);
        gbc.gridy++;
        profilePanel.add(genderLabel, gbc);
        gbc.gridy++;
        profilePanel.add(birthLabel, gbc);
        gbc.gridy++;
        profilePanel.add(phoneNumberLabel, gbc);
        gbc.gridy++;
        profilePanel.add(emailLabel, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        add(profilePanel, BorderLayout.CENTER);

        try (Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student WHERE student_id = 5")) {

            if (rs.next()) {
                idLabel.setText("Student ID:     " + "e2022"+String.format("%03d",rs.getInt("student_id")));
                firstnameLabel.setText("Firstname:     " + rs.getString("student_firstname"));
                lastnameLabel.setText("Lastname:     " + rs.getString("student_lastname"));
                genderLabel.setText("Gender:     " + rs.getString("gender"));
                birthLabel.setText("Birth:     " + rs.getString("student_birth"));
                phoneNumberLabel.setText("Phone Number:     " + rs.getString("phone_number"));
                emailLabel.setText("Email:     " + rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(profilePanel, "Error: " + e.getMessage());
        }

        JButton ViewProfile = new JButton("View Profile");
        ViewProfile.setFont(new Font("Arial", Font.BOLD, 13));
        ViewProfile.setPreferredSize(new Dimension(160, 30));
        ViewProfile.setFocusPainted(false);
        ViewProfile.setBackground(Color.GRAY);
        ViewProfile.setForeground(Color.WHITE);
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
                    c4.show(mainPanel, "student");
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
    

    public class User{
        public int ID;
        public String firstName;
        public String lastName;
        public String Gender;
        public String Birth;
        public String phone_number;
        public String email;
    }
}
