import java.awt.*;
import javax.swing.*;



public final class JFrame1 extends JFrame{
    private JMenuBar menuBar;

    // Home
    private JMenu homeMenu;
    private JMenuItem newMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;

    //Student
    private JMenu studentMenu;
    private JMenuItem viewMenuItem;
    private JMenuItem updateMenuItem;
    private JMenuItem deleteMenuItem;

    //course
    private JMenu courseMenu;
    private JMenuItem viewCourse;
    private JMenuItem updateCourse;
    private JMenuItem searchCourse;
    private JMenuItem deleteCourse;


    public JFrame1() {
        initialize();
    }

    public void initialize() {
        setTitle("Admin");
        setSize(1080, 1080);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // MAIN PANEL
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.blue);

        JPanel mid_panel = new JPanel();
        mid_panel.setBackground(Color.white);
        mid_panel.setPreferredSize(new Dimension(1000, 600));
        
        
        panel.add(mid_panel,BorderLayout.CENTER);

        // Top panel
        JPanel top_panel = new JPanel();
        top_panel.setBackground(Color.white);
        top_panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));


        // Profile panel
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.green);
        //profilePanel.setLayout(new FlowLayout(FlowLayout.LEAD, 10, 10));

        // Profile picture
        ImageIcon profileIcon = new ImageIcon("D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/profile.png");
        profileIcon.setImage(profileIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JLabel profileLabel = new JLabel(profileIcon);

        // User name label
        JLabel AdminLabel = new JLabel("Admin");
        AdminLabel.setFont(new Font("Arial", Font.BOLD, 20));
        AdminLabel.setForeground(Color.white);
        JLabel userNameLabel = new JLabel("Sarith Seyla");
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userNameLabel.setForeground(Color.white);

        profilePanel.add(profileLabel);
        profilePanel.add(AdminLabel,BorderLayout.NORTH);
        profilePanel.add(userNameLabel, BorderLayout.SOUTH);

        top_panel.add(profilePanel);



        JLabel label1 = new JLabel("Student Management System");
        label1.setFont(new Font("Arial", Font.BOLD, 35));
        label1.setForeground(Color.blue);

        ImageIcon labelIcon = new ImageIcon("D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/Logo.png");
        labelIcon.setImage(labelIcon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        label1.setIcon(labelIcon);
        label1.setIconTextGap(10);
        top_panel.add(label1);


        // Change font size for Menu and MeniItem
        Font f = new Font("Times New Romand", Font.PLAIN, 20);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);
        UIManager.put("CheckBoxMenuItem.font", f);
        UIManager.put("RadioButtonMenuItem.font", f);


        menuBar = new JMenuBar();
        menuBar.setBackground(Color.white);
        menuBar.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 10));

        homeMenu =  new JMenu("Home");
        homeMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon homeIcon = new ImageIcon("D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/home.png");
        homeIcon.setImage(homeIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        homeMenu.setIcon(homeIcon);

        newMenuItem = new JMenuItem("New");
        newMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));

        studentMenu =  new JMenu("Student");
        studentMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon studentIcon = new ImageIcon("D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/student.png");
        studentIcon.setImage(studentIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        studentMenu.setIcon(studentIcon);

        viewMenuItem = new JMenuItem("View");
        viewMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // View Students Button Action
        viewMenuItem.addActionListener(e -> {
           // mainFrame.dispose();
            view_sts.showViewStudentUI();
        });
        updateMenuItem = new JMenuItem("Update");
        updateMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));

        courseMenu =  new JMenu("Course");
        courseMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon courseIcon = new ImageIcon("D:/Assignments/I3/Testing/JDBC/Admin/src/Icon/course.png");
        courseIcon.setImage(courseIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        courseMenu.setIcon(courseIcon);

        viewCourse = new JMenuItem("View");
        viewCourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateCourse = new JMenuItem("Update");
        updateCourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchCourse = new JMenuItem("Search");
        searchCourse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deleteCourse = new JMenuItem("Delete");
        deleteCourse.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // JButton Logout = new JButton("Logout");
        // Logout.setFont(new Font("Arial", Font.BOLD, 13));
        // Logout.setPreferredSize(new Dimension(160, 30));
        // Logout.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         int response = JOptionPane.showConfirmDialog(
        //             null, 
        //             "Do you want to log out?", 
        //             "Log Out", 
        //             JOptionPane.YES_NO_OPTION
        //         );
                
        //         if (response == JOptionPane.YES_OPTION) {
        //             CardLayout c4 = (CardLayout) mainPanel.getLayout();
        //             c4.show(mainPanel, "loginScreen");
        //         } else {
        //             System.out.println("Stayed logged in");
        //         }
        //     }
        // });


        homeMenu.add(newMenuItem);
        homeMenu.add(saveMenuItem); 
        homeMenu.add(exitMenuItem);

        studentMenu.add(viewMenuItem);
        studentMenu.add(updateMenuItem); 
        studentMenu.add(deleteMenuItem);

        courseMenu.add(viewCourse);
        courseMenu.add(updateCourse);
        courseMenu.add(searchCourse);
        courseMenu.add(deleteCourse);

        menuBar.add(homeMenu);
        menuBar.add(studentMenu);
        menuBar.add(courseMenu);
        add(menuBar, BorderLayout.SOUTH);

        


        add(top_panel, BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);
        setVisible(true);
 

    }


 

}

