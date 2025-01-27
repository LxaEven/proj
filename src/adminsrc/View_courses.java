package adminsrc;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import main.DBConnect;

public class View_courses extends JPanel {
    private Connection connection;

    public View_courses(Connection connection) {
        this.connection = connection;
    }
    JPanel ViewCoursePanel() {
         // Table Setup
         DefaultTableModel tableModel = new DefaultTableModel();
         tableModel.addColumn("subject_ID");
         tableModel.addColumn("subject");
         tableModel.addColumn("hour/week");
         tableModel.addColumn("hour/semester");

         JTable table = new JTable(tableModel);
 
         JPanel panel = new JPanel();
         panel.setLayout(new BorderLayout());
         panel.add(table, BorderLayout.SOUTH);
         JScrollPane scrollPane = new JScrollPane(table);
         panel.add(scrollPane, BorderLayout.CENTER);

         //======================================================================

        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(20);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        table.setFillsViewportHeight(true);
        
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 13));
        searchLabel.setPreferredSize(new Dimension(50, 30));
        JTextField searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(600, 30));
        String placeholder = "Enter your search here...";
        searchField.setText(placeholder);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 13));
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.setFocusPainted(false);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String searchText = searchField.getText();
                if (searchText.trim().length() == 0) {
                    rowSorter.setRowFilter(null); 
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); 
                }
            }
        });

        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                searchField.setText("");
            }
        
            public void focusLost(FocusEvent e) {
                searchField.setText(placeholder);
            }
        });

        
    
        JPanel SearchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        SearchPanel.add(searchLabel, gbc);
        gbc.gridx++;
        SearchPanel.add(searchField, gbc);
        gbc.gridx++;
        SearchPanel.add(searchButton, gbc);

         panel.add(SearchPanel, BorderLayout.NORTH);

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                try (Connection conn = DBConnect.getConnection();
                    Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM course")) {

                    tableModel.setRowCount(0);

                    // Populate table with data
                    while (rs.next()) {
                        tableModel.addRow(new Object[]{
                        rs.getString("subject_ID"),
                        rs.getString("subject"),
                        rs.getString("hour_per_week"),
                        rs.getString("hour_per_semester")
                });
            }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        return panel;
    }
}
