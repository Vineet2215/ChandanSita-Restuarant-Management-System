import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Loading extends JFrame implements ActionListener {
    JLabel label;
    JButton button;

    Loading() {
        // Setting up the frame
        setTitle("Loading...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        getContentPane().setBackground(new Color(0xFAFAFA));
        
        // Creating and customizing the components
        ImageIcon logoIcon = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/favicon_io/logo.png");
        button = new JButton("WELCOME TO CHANDANSITA'S!", logoIcon);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("ROMALIKA", Font.BOLD, 60));
        button.setForeground(Color.RED);
        button.setBackground(new Color(0xFAFAFA));
        button.setIconTextGap(100);

        // Adding the button to the frame
        add(button);

        // Setting up the frame visibility
        ImageIcon favicon = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/favicon_io/icondis.png");
        setIconImage(favicon.getImage());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            dispose();

            // Navigate to the FoodFrame Class in FoodPage
            new FoodFrame();
        }
    }
}

public class LoadingPage
{
    public static void main(String[] args)
    {

        new Loading();
    }
}