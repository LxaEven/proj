package student;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class displayCourse extends JPanel{
    GridBagConstraints gbc = new GridBagConstraints();
    JPanel Panel;

    public displayCourse(JPanel Panel){
        setLayout(new BorderLayout());
        CourseDisplay();
    }

    public void CourseDisplay(){
        DefaultTableModel CourseTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Table Setup
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("subject_ID");
        tableModel.addColumn("subject");
        tableModel.addColumn("hour/week");
        tableModel.addColumn("hour/semester");

        JTable CourseTable = new JTable(tableModel);
        
        try (Connection conn = main.DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM `course`")) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("subject_ID"),
                    rs.getString("subject"),
                    rs.getString("hour_per_week"),
                    rs.getString("hour_per_semester")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(Panel, "Error: " + e.getMessage());
        }

        
        CourseTable.setFont(new Font("Arial", Font.PLAIN, 16));
        CourseTable.setRowHeight(30);
        CourseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        CourseTable.getTableHeader().setBackground(Color.CYAN);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < CourseTable.getColumnCount(); i++) {
            CourseTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        CourseTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        CourseTable.setRowSorter(rowSorter);
        CourseTable.setBackground(new Color(173, 216, 230));
        CourseTable.setFillsViewportHeight(true);

        JLabel SearchLabel = new JLabel("Search:");
        SearchLabel.setFont(new Font("Arial", Font.BOLD, 15));
        JTextField SearchField = new JTextField(20);
        SearchField.setPreferredSize(new Dimension(600, 30));
        String placeholder = "Enter your search here...";
        SearchField.setText(placeholder);

        SearchField.addKeyListener((KeyListener) new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = SearchField.getText().trim();
                if (searchText.isEmpty() || searchText.equals(placeholder)) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });

        SearchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (SearchField.getText().equals(placeholder)) {
                    SearchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (SearchField.getText().trim().isEmpty()) {
                    SearchField.setText(placeholder);
                }
            }
        });

        JButton SearchButton = new JButton("Search");
        SearchButton.setFont(new Font("Arial", Font.BOLD, 15));
        SearchButton.setPreferredSize(new Dimension(100, 30));
        SearchButton.addActionListener(e -> {
            String searchText = SearchLabel.getText().trim();
            if (searchText.isEmpty() || searchText.equals(placeholder)) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        });

        JScrollPane scrollPanel = new JScrollPane(CourseTable);
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(new Color(173, 216, 230));
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(SearchLabel, gbc);
        gbc.gridx++;
        searchPanel.add(SearchField, gbc);
        gbc.gridx++;
        searchPanel.add(SearchButton, gbc);

        JPanel tablePanel = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        tablePanel.add(searchPanel, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tablePanel.add(scrollPanel, gbc);
        tablePanel.setBackground(new Color(173, 216, 230));
        add(tablePanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    
    }
}
