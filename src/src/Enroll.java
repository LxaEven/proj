import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class Enroll extends JPanel {
    private Connection connection;

    public Enroll(Connection connection) {
        this.connection = connection;
    }

    public JPanel getEnrollPanel() {
        return createAddStudentPanel();
    }

    private JPanel createAddStudentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JTextField txtFirstName = new JTextField();
        JTextField txtLastName = new JTextField();
        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Male", "Female"});
        JTextField txtDob = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtAddress = new JTextField();
        JTextField txtPhoneNumber = new JTextField();
        JButton btnSubmit = new JButton("Submit");

        panel.add(new JLabel("First Name:"));
        panel.add(txtFirstName);
        panel.add(new JLabel("Last Name:"));
        panel.add(txtLastName);
        panel.add(new JLabel("Gender:"));
        panel.add(cbGender);
        panel.add(new JLabel("Date of Birth:"));
        panel.add(txtDob);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Address:"));
        panel.add(txtAddress);
        panel.add(new JLabel("Phone Number:"));
        panel.add(txtPhoneNumber);
        panel.add(new JLabel()); // Empty cell
        panel.add(btnSubmit);

        btnSubmit.addActionListener(e -> {
            try {
                String query = "INSERT INTO students (first_name, last_name, gender, dob, email, address, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1, txtFirstName.getText());
                pst.setString(2, txtLastName.getText());
                pst.setString(3, cbGender.getSelectedItem().toString());
                pst.setString(4, txtDob.getText());
                pst.setString(5, txtEmail.getText());
                pst.setString(6, txtAddress.getText());
                pst.setString(7, txtPhoneNumber.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Student added successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }
}
