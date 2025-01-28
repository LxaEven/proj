package adminsrc;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import login.MainPanel;
import main.*;


public final class JFrame1 extends JFrame{
    private Connection connection;

    private JPanel mainPanel;
    private JPanel midPanel; 
    private CardLayout cardLayout;
    private JMenuBar menuBar;
    private DefaultTableModel tableModel;


    // Other Panels
    private Home homePanel;
    private Enroll enrollPanel;
    private view_sts viewPanel;
    private StudentUpdate updatePanel;
    private delete deletePanel;


    // Home
    private JMenu homeMenu;
    private JMenuItem newMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem logOutMenuItem;

    //Student
    private JMenu studentMenu;
    private JMenuItem viewStudent;
    private JMenuItem addStudent;
    private JMenuItem updateStudent;
    private JMenuItem deleteStudent;

    //course
    private JMenu courseMenu;
    private JMenuItem viewCourse;
    private JMenuItem addCourse;
    private JMenuItem updateCourse;
    private JMenuItem deleteCourse;

    //setting
    private JMenu settingMenu;
    private JMenuItem darkMenu;
    private JMenuItem lightMenu;
    private JMenuItem exitMenu;


    public JFrame1() {
        initializeDatabaseConnection();
        initialize();
    }

    private void initializeDatabaseConnection() {
        setLayout(new BorderLayout());
        try {
            DBConnect.getConnection();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }
    }
 
    public void initialize() {
        setTitle("Admin");
        setSize(1080, 1080);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        mainPanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel();
        mainPanel = new JPanel(new BorderLayout());
        cardLayout = new CardLayout(10,10);
        midPanel = new JPanel(cardLayout); // Initialize midPanel with CardLayout

       // mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        mainPanel.setBackground(Color.blue);

        // Middle panel (Content Area)
        midPanel.setBackground(Color.black);
        midPanel.setPreferredSize(new Dimension(1080, 600));
        mainPanel.add(midPanel, BorderLayout.SOUTH);

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.green);
        menuBar.setLayout(new GridLayout(1, 5));


        
       // JPanel viewSts_panel = createViewStudentPanel();

        homePanel = new Home(connection);
        enrollPanel = new Enroll(connection);
        viewPanel = new view_sts(connection);
        deletePanel = new delete(connection);
        updatePanel = new StudentUpdate(connection);

        // JPanel addSts_panel = EnrollmentPanel();
        // JPanel updateSts_panel = UpdateStudentPanel();
        // JPanel deletePanel = DeleteStudentPanel();
        midPanel.add(homePanel.homePanel(), "Home");

        // Student Panel
        midPanel.add(viewPanel.ViewStudentPanel(), "ViewSts");
        midPanel.add(enrollPanel.getEnrollPanel(), "Enroll");
        midPanel.add(updatePanel.createUpdateStudentPanel(), "UpdateSts");
        midPanel.add(deletePanel.createDeleteStudentPanel(), "DeleteSts");

        // Course Panel
        midPanel.add(new View_courses(connection).ViewCoursePanel(), "ViewCourse");
        midPanel.add(new AddCourse(connection).getAddCoursePanel(), "AddCourse");
        midPanel.add(new UpdateCourse(connection).getUpdateCoursePanel(), "UpdateCourse");
        midPanel.add(new DeleteCourse(connection).createDeleteCoursePanel(), "DeleteCourse");



        

        // Home
        homeMenu =  new JMenu("Home");
        ImageIcon homeIcon = new ImageIcon("image/home.png");
        homeMenu.setIcon(homeIcon);

        newMenuItem = new JMenuItem("Home");
        newMenuItem.addActionListener(e -> cardLayout.show(midPanel, "Home"));
        saveMenuItem = new JMenuItem("Save");
        logOutMenuItem = new JMenuItem("Log out");

        // Student
        studentMenu =  new JMenu("Student");
        ImageIcon studentIcon = new ImageIcon("image/student.png");
        studentMenu.setIcon(studentIcon);
        viewStudent = new JMenuItem("View");
        viewStudent.addActionListener(e -> cardLayout.show(midPanel, "ViewSts"));
        
        addStudent = new JMenuItem("Enroll");
        addStudent.addActionListener(e -> cardLayout.show(midPanel, "Enroll"));
        updateStudent = new JMenuItem("Update");
        updateStudent.addActionListener(e -> cardLayout.show(midPanel, "UpdateSts"));
        deleteStudent = new JMenuItem("Delete");
        deleteStudent.addActionListener(e -> cardLayout.show(midPanel, "DeleteSts"));



        // Course
        courseMenu =  new JMenu("Course");
        ImageIcon courseIcon = new ImageIcon("image/course.png");
        courseMenu.setIcon(courseIcon);
        viewCourse = new JMenuItem("View");
        viewCourse.addActionListener(e -> cardLayout.show(midPanel, "ViewCourse"));
        addCourse = new JMenuItem("Add");
        addCourse.addActionListener(e -> cardLayout.show(midPanel, "AddCourse"));
        updateCourse = new JMenuItem("Update");
        updateCourse.addActionListener(e -> cardLayout.show(midPanel, "UpdateCourse"));
        deleteCourse = new JMenuItem("Delete");
        deleteCourse.addActionListener(e -> cardLayout.show(midPanel, "DeleteCourse"));

        // Setting
        settingMenu =  new JMenu("Setting");
        ImageIcon settingIcon = new ImageIcon("image/course.png");
        settingMenu.setIcon(settingIcon);
        darkMenu = new JMenuItem("Dark mode");
        lightMenu = new JMenuItem("Light mode");
        exitMenu = new JMenuItem("Exit");


        JMenuItem[] item = {homeMenu, studentMenu, newMenuItem, courseMenu, saveMenuItem, logOutMenuItem, viewStudent, addStudent, updateStudent, deleteStudent,viewCourse, updateCourse, addCourse, deleteCourse};
        for (JMenuItem item1 : item) {
            item1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        ImageIcon[] icons = {homeIcon, studentIcon, courseIcon, settingIcon};
        for (ImageIcon icon : icons) {
            icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        }


        homeMenu.add(newMenuItem);
        homeMenu.add(saveMenuItem); 
        homeMenu.add(logOutMenuItem);

        studentMenu.add(viewStudent);
        studentMenu.add(addStudent);
        studentMenu.add(updateStudent); 
        studentMenu.add(deleteStudent);

        courseMenu.add(viewCourse);
        courseMenu.add(addCourse);
        courseMenu.add(updateCourse);
        courseMenu.add(deleteCourse);

        settingMenu.add(darkMenu);
        settingMenu.add(lightMenu);
        settingMenu.add(exitMenu);

        menuBar.add(homeMenu);
        menuBar.add(studentMenu);
        menuBar.add(courseMenu);
        menuBar.add(settingMenu);
        







        // Top panel
        JPanel top_panel = new JPanel(new BorderLayout());
        top_panel.setBackground(Color.white);
        top_panel.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));


        // Profile panel
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.green);
        profilePanel.setPreferredSize(new Dimension(250, 100));

        // Profile picture
        ImageIcon profileIcon = new ImageIcon("image/profile.png");
        profileIcon.setImage(profileIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        JLabel profileLabel = new JLabel(profileIcon);

        // User name label
        JLabel AdminLabel = new JLabel("Admin");
        AdminLabel.setFont(new Font("Arial", Font.BOLD, 20));
        AdminLabel.setForeground(Color.white);
        JLabel userNameLabel = new JLabel("Sarith Seyla");
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        userNameLabel.setForeground(Color.white);

        profilePanel.add(profileLabel);
        profilePanel.add(AdminLabel,BorderLayout.CENTER);
        profilePanel.add(userNameLabel, BorderLayout.SOUTH);


        top_panel.add(profilePanel, BorderLayout.WEST);



        JLabel label1 = new JLabel("Student Management System");
        label1.setFont(new Font("Arial", Font.BOLD, 35));
        label1.setForeground(Color.blue);

        ImageIcon labelIcon = new ImageIcon("image/Logo.png");
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


        

        
        
        this.add(top_panel, BorderLayout.NORTH);
        this.add(mainPanel,BorderLayout.CENTER);
        this.add(menuBar, BorderLayout.SOUTH);
        setVisible(true);
 

    }
 

}

