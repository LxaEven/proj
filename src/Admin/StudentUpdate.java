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
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fonts for UI elements
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font inputFont = new Font("Arial", Font.PLAIN, 15);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Input fields
        JTextField txtStudentId = new JTextField();
        JTextField txtFirstName = new JTextField();
        JTextField txtLastName = new JTextField();
        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Male", "Female"});
        JTextField txtDob = new JTextField();
        JTextField txtAddress = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtPhoneNumber = new JTextField();
        JComboBox<String> cbDepartment = new JComboBox<>(new String[]{"D001", "D002", "D003", "D004", "D005"});

        // Buttons
        JButton btnSearch = new JButton("Search");
        JButton btnUpdate = new JButton("Update");

        // Set fonts for input fields and buttons
        txtStudentId.setFont(inputFont);
        txtFirstName.setFont(inputFont);
        txtLastName.setFont(inputFont);
        cbGender.setFont(inputFont);
        txtDob.setFont(inputFont);
        txtAddress.setFont(inputFont);
        txtEmail.setFont(inputFont);
        txtPhoneNumber.setFont(inputFont);
        cbDepartment.setFont(inputFont);

        btnSearch.setFont(buttonFont);
        btnSearch.setBackground(Color.CYAN);
        btnUpdate.setFont(buttonFont);
        btnUpdate.setBackground(Color.GREEN);

        // Create and style labels
        JLabel lblStudentId = new JLabel("Student ID:");
        JLabel lblFirstName = new JLabel("First Name:");
        JLabel lblLastName = new JLabel("Last Name:");
        JLabel lblGender = new JLabel("Gender:");
        JLabel lblDob = new JLabel("Date of Birth:");
        JLabel lblAddress = new JLabel("Address:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        JLabel lblDepartment = new JLabel("Department:");

        JLabel[] labels = {lblStudentId, lblFirstName, lblLastName, lblGender, lblDob, lblAddress, lblEmail, lblPhoneNumber, lblDepartment};

        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(labelFont);
        }

        // Add components to the panel
        panel.add(lblStudentId);
        panel.add(txtStudentId);
        panel.add(lblFirstName);
        panel.add(txtFirstName);
        panel.add(lblLastName);
        panel.add(txtLastName);
        panel.add(lblGender);
        panel.add(cbGender);
        panel.add(lblDob);
        panel.add(txtDob);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblAddress);
        panel.add(txtAddress);
        panel.add(lblPhoneNumber);
        panel.add(txtPhoneNumber);
        panel.add(lblDepartment);
        panel.add(cbDepartment); 
        panel.add(btnSearch);
        panel.add(btnUpdate);

        // Search Action
        btnSearch.addActionListener(e -> {
            String query = "SELECT * FROM student WHERE student_id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, Integer.parseInt(txtStudentId.getText()));
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    txtFirstName.setText(rs.getString("student_firstname"));
                    txtLastName.setText(rs.getString("student_lastname"));
                    cbGender.setSelectedItem(rs.getString("gender"));
                    txtDob.setText(rs.getString("student_birth"));
                    txtAddress.setText(rs.getString("student_address"));
                    txtEmail.setText(rs.getString("student_email"));
                    txtPhoneNumber.setText(rs.getString("phone_number"));
                    cbDepartment.setSelectedItem(rs.getString("department"));
                } else {
                    JOptionPane.showMessageDialog(panel, "Student not found!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Update Action
        btnUpdate.addActionListener(e -> {
            String query = "UPDATE student SET student_firstname = ?, student_lastname = ?, gender = ?, student_birth = ?, student_address = ?, student_email = ?, phone_number = ?, department = ? WHERE student_id = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                pst.setString(1, txtFirstName.getText());
                pst.setString(2, txtLastName.getText());
                pst.setString(3, cbGender.getSelectedItem().toString());
                pst.setString(4, txtDob.getText());
                pst.setString(5, txtAddress.getText());
                pst.setString(6, txtEmail.getText());
                pst.setString(7, txtPhoneNumber.getText());
                pst.setString(8, cbDepartment.getSelectedItem().toString());
                pst.setInt(9, Integer.parseInt(txtStudentId.getText()));
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(panel, "Student updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(panel, "No records were updated!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }

            // Clear the fields after submission
            txtFirstName.setText("");
            txtLastName.setText("");
            cbGender.setSelectedIndex(-1);
            txtDob.setText("");
            txtEmail.setText("");
            txtAddress.setText("");
            txtPhoneNumber.setText("");
            cbDepartment.setSelectedIndex(-1);

        });

        return panel;
    }
}
