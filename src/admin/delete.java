package admin;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class delete {
    public JPanel createDeleteStudentPanel() {
    JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JTextField txtStudentId = new JTextField();
    JButton btnDelete = new JButton("Delete");

    panel.add(new JLabel("Enter Student ID to Delete:"));
    panel.add(txtStudentId);
    panel.add(new JLabel()); // Empty cell
    panel.add(btnDelete);

    // Delete action
    btnDelete.addActionListener(e -> {
        try {
            String query = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(txtStudentId.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(panel, "Student deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(panel, "Student not found!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    return panel;
}

}
