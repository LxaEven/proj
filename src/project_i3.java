import javax.swing.*;

import login.MainPanel;


public class project_i3 extends JFrame {

    public static void main(String[] args) {
        

        JFrame frame = new JFrame("Student management system");
        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        frame.add(new MainPanel());
        frame.setVisible(true);
        

    }
} 