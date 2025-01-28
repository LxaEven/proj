package student;

import java.awt.*;
import javax.swing.*;


public class studentFrame extends JFrame {
    public studentFrame() {
        setTitle("Student");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new GridLayout());
        student student = new student(mainPanel);
        mainPanel.add(student);
        add(mainPanel);
        setVisible(false);
    }

    public static void main(String[] args) {
        new studentFrame();
    }
    
}
