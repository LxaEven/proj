import java.awt.*;
import java.sql.*;
import javax.swing.*;



public class UpdateCourse extends JPanel {
    private Connection connection;

    public UpdateCourse(Connection connection) {
        this.connection = connection;
    }

    public JPanel getUpdateCoursePanel() {
        return createUpdateCoursePanel();
    }

    private JPanel createUpdateCoursePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Fonts for UI elements
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font inputFont = new Font("Arial", Font.PLAIN, 15);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        // Input fields
        JTextField txtSub_ID = new JTextField();
        JTextField txtSubject = new JTextField();
        JTextField txtHour_week = new JTextField();
        JTextField txtHour_semester = new JTextField();

        // Buttons
        JButton btnSearch = new JButton("Search");
        JButton btnUpdate = new JButton("Update");

        // Set fonts for input fields and buttons
        txtSub_ID.setFont(inputFont);
        txtSubject.setFont(inputFont);
        txtHour_week.setFont(inputFont);
        txtHour_semester.setFont(inputFont);

        btnSearch.setFont(buttonFont);
        btnSearch.setBackground(Color.CYAN);
        btnUpdate.setFont(buttonFont);
        btnUpdate.setBackground(Color.GREEN);

        // Create and style labels
        JLabel lblSub_ID = new JLabel("Subject ID:");
        JLabel lblSubject = new JLabel("Subject:");
        JLabel lblHour_week = new JLabel("Hour per Week:");
        JLabel lblHour_semester = new JLabel("Hour per Semester:");

        JLabel[] labels = {lblSub_ID, lblSubject, lblHour_week, lblHour_semester};

        for (JLabel label : labels) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(labelFont);
        }

        // Add components to the panel
        panel.add(lblSub_ID);
        panel.add(txtSub_ID);
        panel.add(lblSubject);
        panel.add(txtSubject);
        panel.add(lblHour_week);
        panel.add(txtHour_week);
        panel.add(lblHour_semester);
        panel.add(txtHour_semester);
        panel.add(btnSearch);
        panel.add(btnUpdate);

        // Search Action
        btnSearch.addActionListener(e -> {
            String query = "SELECT * FROM course WHERE subject_ID = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {
           
                pst.setString(1, txtSub_ID.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    txtSubject.setText(rs.getString("subject"));
                    txtHour_week.setText(String.valueOf(rs.getInt("hour_per_week")));
                    txtHour_semester.setText(String.valueOf(rs.getInt("hour_per_semester")));
                } else {
                    JOptionPane.showMessageDialog(panel, "Student not found!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Update Action
         btnUpdate.addActionListener(e -> {
            String query = "UPDATE course SET subject = ?, hour_per_week = ?, hour_per_semester = ? WHERE subject_ID = ?";
            try (Connection conn = DBConnect.getConnection();
                 PreparedStatement pst = conn.prepareStatement(query)) {

                pst.setString(1, txtSubject.getText());
                pst.setString(2, txtHour_week.getText());
                pst.setString(3, txtHour_semester.getText());
                pst.setString(4, txtSub_ID.getText());


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
            txtSub_ID.setText("");
            txtSubject.setText("");
            txtHour_week.setText("");
            txtHour_semester.setText("");

        });

        return panel;
    }
}

