import java.sql.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Lgf extends JFrame implements ActionListener

{
    // Define database connection parameters
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/java_chandansita_project";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "";

    JLabel userLabel = new JLabel("USERNAME:");
    JLabel welcome = new JLabel("WELCOME TO CHANDANSITA'S");
    JLabel intro = new JLabel("Please provide us with your details!");
    JTextField userTextField = new JTextField();
    JButton loginButton = new JButton();
    String name = "";
    boolean isNewUser;
    boolean p;
    static String user_name;

    Lgf() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("ChandanSita's Login Portal");
        ImageIcon FAVICON = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/favicon_io/icondis.png");
        this.setIconImage(FAVICON.getImage());
        this.getContentPane().setBackground(Color.pink);

        setLocationAndSize();
        addComponents();
        addActionEvent();
        miscellaneous();
    }

    public void setLocationAndSize() {
        userLabel.setBounds(520, 250, 300, 50);
        userLabel.setFont(new Font("ROMALIKA", Font.ITALIC, 60));
        welcome.setBounds(250, 80, 1200, 80);
        welcome.setFont(new Font("ROMALIKA", Font.BOLD, 100));
        userTextField.setBounds(850, 250, 300, 50);
        loginButton.setBounds(310, 350, 1000, 500);
        loginButton.setFocusable(false);
    }

    public void addComponents()

    {
        // Lgf
        this.add(userLabel);
        this.add(welcome);
        this.add(userTextField);
        this.add(loginButton);
        this.add(intro);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
    }

    public void miscellaneous() {

        ImageIcon lg = new ImageIcon("E:/SEM#3/OOPS/Book Stuff/Swing/Food Icons/n1.png");
        loginButton.setIcon(lg);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.black, 10, true));

    }

    static File f;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            name = userTextField.getText();
            isNewUser = !isUserExist(name);
            // System.out.println(name);
            user_name = name;
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "The name seems to be empty!", "Invalid Name Input",
                        JOptionPane.ERROR_MESSAGE);
            }

            else {

                String fname = name + ".txt";
                f = new File(fname);

                if (isNewUser) {
                    createUserInDatabase(name);
                    JOptionPane.showMessageDialog(this, "The user " + name + " is a new Customer, Welcome!",
                            "Creating Entry...", JOptionPane.PLAIN_MESSAGE);

                    p = true;

                    try {

                        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                        bw.write(""); // true to append, false or nothing to rewrite
                        bw.close();
                    } catch (IOException exc) {
                        System.out.println("Error!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "The user " + name + " is already registered, Welcome!",
                            "User Found!", JOptionPane.INFORMATION_MESSAGE);
                    p = false;
                }

                dispose();

                // Now opening the Loading in Loading Page 
                new Loading();
            }
        }
    }

  

    private boolean isUserExist(String username) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String selectQuery = "SELECT * FROM user_table WHERE username = ?";
            String updateQuery = "UPDATE user_table SET timestamp = ? WHERE username = ?";
            
            // Check if the user exists
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
                selectStmt.setString(1, username);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    if (rs.next()) {
                        // User exists, update the timestamp
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                            updateStmt.setTimestamp(1, timestamp);
                            updateStmt.setString(2, username);
                            updateStmt.executeUpdate();
                        }
                        return true;
                    } else {
                        // User does not exist
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    // Method to create a new user in the database
    private void createUserInDatabase(String username) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_table (username) VALUES (?)")) {
            stmt.setString(1, username);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public class LoginPage
{
    public static void main(String[] args) {

        new Lgf();
    }
}
