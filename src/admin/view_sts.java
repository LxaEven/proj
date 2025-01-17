package admin;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class view_sts extends JPanel {
    private Connection connection;

    public view_sts(Connection connection) {
        this.connection = connection;
    }
    public JPanel createViewStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Table Setup
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Date of Birth");
        tableModel.addColumn("Email");
        tableModel.addColumn("Address");
        tableModel.addColumn("Phone Number");
        JTable table = new JTable(tableModel);

        panel.add(table);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            // Clear existing data
            tableModel.setRowCount(0);

            // Populate table with data
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("gender"),
                    rs.getDate("dob"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("phone_number")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return panel;
    }
}
