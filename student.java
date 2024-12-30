import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class student extends JPanel {

    public student(JPanel mainPanel){
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Student", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JLabel result = new JLabel("Result: ", SwingConstants.CENTER);
        result.setFont(new Font("Arial", Font.PLAIN, 20));
        int Result = 5 + 5;
        result.setText("Result: " + Result);
        add(result, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                CardLayout c5 = (CardLayout) mainPanel.getLayout();
                c5.show(mainPanel, "login");
                
                
                
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
