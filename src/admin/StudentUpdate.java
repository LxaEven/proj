package admin;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class StudentUpdate extends JPanel {

    private Connection connection;

    public StudentUpdate(Connection connection) {
        this.connection = connection;
    }
    public JPanel createUpdateStudentPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(9, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    // Input fields
    JTextField txtStudentId = new JTextField();
    JTextField txtFirstName = new JTextField();
    JTextField txtLastName = new JTextField();
    JComboBox<String> cbGender = new JComboBox<>(new String[]{"Male", "Female"});
    JTextField txtDob = new JTextField();
    JTextField txtEmail = new JTextField();
    JTextField txtAddress = new JTextField();
    JTextField txtPhoneNumber = new JTextField();

    JButton btnSearch = new JButton("Search");
    JButton btnUpdate = new JButton("Update");

    // Add components to panel
    panel.add(new JLabel("Student ID:"));
    panel.add(txtStudentId);
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
    panel.add(btnSearch);
    panel.add(btnUpdate);

    // Search Action
    btnSearch.addActionListener(e -> {
        try {
            String query = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(txtStudentId.getText()));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txtFirstName.setText(rs.getString("first_name"));
                txtLastName.setText(rs.getString("last_name"));
                cbGender.setSelectedItem(rs.getString("gender"));
                txtDob.setText(rs.getString("dob"));
                txtEmail.setText(rs.getString("email"));
                txtAddress.setText(rs.getString("address"));
                txtPhoneNumber.setText(rs.getString("phone_number"));
            } else {
                JOptionPane.showMessageDialog(panel, "Student not found!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Update Action
    btnUpdate.addActionListener(e -> {
        try {
            String query = "UPDATE students SET first_name = ?, last_name = ?, gender = ?, dob = ?, email = ?, address = ?, phone_number = ? WHERE student_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, txtFirstName.getText());
            pst.setString(2, txtLastName.getText());
            pst.setString(3, cbGender.getSelectedItem().toString());
            pst.setString(4, txtDob.getText());
            pst.setString(5, txtEmail.getText());
            pst.setString(6, txtAddress.getText());
            pst.setString(7, txtPhoneNumber.getText());
            pst.setInt(8, Integer.parseInt(txtStudentId.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(panel, "Student updated successfully!");
            } else {
                JOptionPane.showMessageDialog(panel, "No records were updated!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    return panel;
}

}
