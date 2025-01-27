package fail_code;

import javax.swing.*;

import main.DBConnect;
import main.MainPanel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class viewProfile extends JPanel {
    private JLabel idLabel = new JLabel("Student ID:   ");
    private JLabel firstnameLabel = new JLabel("Firstname:   ");
    private JLabel lastnameLabel = new JLabel("Lastname:   ");
    private JLabel genderLabel = new JLabel("Gender:   ");
    private JLabel birthLabel = new JLabel("Birth:   ");
    private JLabel phoneNumberLabel = new JLabel("Phone Number:   ");
    private JLabel emailLabel = new JLabel("Email:   ");

    private GridBagConstraints gbc = new GridBagConstraints();

    public viewProfile(MainPanel mainPanel) {
        setLayout(new BorderLayout());

        // Logo setup
        ImageIcon imageIcon = new ImageIcon("image//logo.jpg");
        Image resizedImage = imageIcon.getImage().getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));

        // Buttons
        JButton viewProfileButton = createButton("View Profile", e -> mainPanel.showScreen("ViewProfile"));
        JButton viewScoreButton = createButton("View Score", e -> mainPanel.showScreen("ViewScore"));
        JButton viewCourseButton = createButton("View Course", e -> mainPanel.showScreen("ViewCourse"));
        JButton changePasswordButton = createButton("Change Password", e -> mainPanel.showScreen("ChangePassword"));

        JButton logoutButton = createButton("Logout", e -> {
            int response = JOptionPane.showConfirmDialog(
                this,
                "Do you want to log out?",
                "Log Out",
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                mainPanel.showScreen("loginScreen");
            }
        });

        JButton closeProgramButton = createButton("Exit", e -> {
            int response = JOptionPane.showConfirmDialog(
                this,
                "Do you want to exit the program?",
                "Exit Program",
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Profile info panel
        JPanel profilePanel = new JPanel(new GridBagLayout());
        profilePanel.setBackground(Color.WHITE);
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        addLabel(profilePanel, idLabel);
        addLabel(profilePanel, firstnameLabel);
        addLabel(profilePanel, lastnameLabel);
        addLabel(profilePanel, genderLabel);
        addLabel(profilePanel, birthLabel);
        addLabel(profilePanel, phoneNumberLabel);
        addLabel(profilePanel, emailLabel);

        // Button panel
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.GRAY);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(Color.GRAY);
        logoPanel.add(logoLabel);

        JPanel buttonsContainer = new JPanel(new GridBagLayout());
        buttonsContainer.setBackground(Color.GRAY);
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridy = 0;

        buttonsContainer.add(viewProfileButton, gbc);
        gbc.gridy++;
        buttonsContainer.add(viewScoreButton, gbc);
        gbc.gridy++;
        buttonsContainer.add(viewCourseButton, gbc);
        gbc.gridy++;
        buttonsContainer.add(changePasswordButton, gbc);
        gbc.gridy++;
        buttonsContainer.add(logoutButton, gbc);
        gbc.gridy++;
        buttonsContainer.add(closeProgramButton, gbc);

        buttonPanel.add(logoPanel, BorderLayout.NORTH);
        buttonPanel.add(buttonsContainer, BorderLayout.CENTER);

        // Adding panels
        add(buttonPanel, BorderLayout.WEST);
        add(profilePanel, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(Color.GRAY);
        eastPanel.setPreferredSize(new Dimension(50, 300));
        add(eastPanel, BorderLayout.EAST);

        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.GRAY);
        southPanel.setPreferredSize(new Dimension(200, 50));
        add(southPanel, BorderLayout.SOUTH);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(160, 30));
        button.setFocusPainted(false);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.addActionListener(actionListener);
        return button;
    }

    private void addLabel(JPanel panel, JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 17));
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);
        gbc.gridy++;
    }

    public void loadData(MainPanel mainPanel) {
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "SELECT * FROM student WHERE (email = ? OR phone_number = ?) AND student_password = ?")) {

            String identifier = MainPanel.loginUserIdentifier;
            String password = MainPanel.loginUserPassword;

            pstmt.setString(1, identifier);
            pstmt.setString(2, identifier);
            pstmt.setString(3, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    idLabel.setText("Student ID:     e2022" + String.format("%03d", rs.getInt("student_id")));
                    firstnameLabel.setText("Firstname:     " + rs.getString("student_firstname"));
                    lastnameLabel.setText("Lastname:     " + rs.getString("student_lastname"));
                    genderLabel.setText("Gender:     " + rs.getString("gender"));
                    birthLabel.setText("Birth:     " + rs.getString("student_birth"));
                    phoneNumberLabel.setText("Phone Number:     " + rs.getString("phone_number"));
                    emailLabel.setText("Email:     " + rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

        revalidate();
        repaint();
    }
}
