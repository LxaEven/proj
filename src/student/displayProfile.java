package student;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.DBConnect;
import main.MainPanel;

public class displayProfile extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();

    public displayProfile(MainPanel mainPanel) {
        setLayout(new BorderLayout());
        ProfileDisplay();
    }

    public void ProfileDisplay() {
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
        profilePanel.setBackground(new Color(173, 216, 230));

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM student WHERE (student_email = ? OR phone_number = ?) AND student_password = ?")) {

            String identifier = MainPanel.loginUserIdentifier;
            String password = MainPanel.loginUserPassword;

            pstmt.setString(1, identifier);
            pstmt.setString(2, identifier);
            pstmt.setString(3, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    idLabel.setText("Student ID:     " + String.format("%03d", rs.getInt("student_id")));
                    firstnameLabel.setText("Firstname:     " + rs.getString("student_firstname"));
                    lastnameLabel.setText("Lastname:     " + rs.getString("student_lastname"));
                    genderLabel.setText("Gender:     " + rs.getString("gender"));
                    birthLabel.setText("Birth:     " + rs.getString("student_birth"));
                    phoneNumberLabel.setText("Phone Number:     " + rs.getString("phone_number"));
                    emailLabel.setText("Email:     " + rs.getString("student_email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(profilePanel, "Error: " + e.getMessage());
        }
        revalidate();
        repaint();
    }
}
