package Project.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame implements ActionListener {
    public static final String ICON_PATH = "C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\hospital.png";
    public static final String BG_PATH = "C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\bg.png";
    JPasswordField passField;
    JTextField usernameTextField;
    JButton submitbutton;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    LoginFrame() {
        // Set frame properties
        this.setTitle("Login Page");
        setIconImage(new ImageIcon(ICON_PATH).getImage());
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Creating a background panel
        BackgroundPanel registrationbg = new BackgroundPanel(new ImageIcon(BG_PATH).getImage());
        registrationbg.setLayout(null);
        registrationbg.setBounds(0, 0, 500, 500);

        // Creating labels, text fields, and button
        JLabel logolabel = new JLabel("Enter your Credentials to Login.", new ImageIcon(ICON_PATH), JLabel.CENTER);
        logolabel.setVerticalTextPosition(JLabel.BOTTOM);
        logolabel.setHorizontalTextPosition(JLabel.CENTER);

        JLabel usernamelabel = new JLabel("UserName", new ImageIcon("C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\user.png"), JLabel.RIGHT);
        usernamelabel.setForeground(new Color(1));
        usernamelabel.setFont(new Font("Calibri", Font.BOLD, 16));

        usernameTextField = new JTextField(20);

        JLabel passwordlabel = new JLabel("PassWord", new ImageIcon("C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\password.png"), JLabel.RIGHT);
        passwordlabel.setForeground(new Color(1));
        passwordlabel.setFont(new Font("Calibri", Font.BOLD, 16));

        passField = new JPasswordField(20);

        submitbutton = new JButton("LogIn");
        submitbutton.setBackground(new Color(0x204B56));
        submitbutton.setForeground(Color.WHITE);
        submitbutton.setFont(new Font("Calibri", Font.BOLD, 16));
        submitbutton.addActionListener(this);

        // Creating and setting up panels
        JPanel logopanel = new JPanel();
        logopanel.setOpaque(false); // Make the panel transparent
        logopanel.setBounds(0, 0, 500, 150);
        logopanel.add(logolabel);

        JPanel userpanel = new JPanel();
        userpanel.setOpaque(false); // Make the panel transparent
        userpanel.setBounds(15, 150, 150, 50);
        userpanel.add(usernamelabel);

        JPanel usernamepanel = new JPanel();
        usernamepanel.setOpaque(false); // Make the panel transparent
        usernamepanel.setBounds(150, 150, 349, 50);
        usernamepanel.add(usernameTextField);

        JPanel passwordlabelpanel = new JPanel();
        passwordlabelpanel.setOpaque(false); // Make the panel transparent
        passwordlabelpanel.setBounds(15, 250, 150, 50);
        passwordlabelpanel.add(passwordlabel);

        JPanel passwordfieldpanel = new JPanel();
        passwordfieldpanel.setOpaque(false); // Make the panel transparent
        passwordfieldpanel.setBounds(150, 250, 349, 50);
        passwordfieldpanel.add(passField);

        JPanel buttonpanel = new JPanel();
        buttonpanel.setOpaque(false); // Make the panel transparent
        buttonpanel.setBounds(200, 320, 100, 50);
        buttonpanel.add(submitbutton);

        // Adding panels to the background panel
        registrationbg.add(logopanel);
        registrationbg.add(userpanel);
        registrationbg.add(usernamepanel);
        registrationbg.add(passwordlabelpanel);
        registrationbg.add(passwordfieldpanel);
        registrationbg.add(buttonpanel);

        // Adding the background panel to the frame
        add(registrationbg);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameTextField.getText();
        String password = new String(passField.getPassword());
        authenticate(username, password);
    }

    private void authenticate(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare SQL query
            String sql = "SELECT * FROM user_key WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // Execute query
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // User found, credentials are correct so new frame is created i.e welcome frame
                new Welcome();
                this.dispose();
            } else {
                // User not found, credentials are incorrect
                JOptionPane.showMessageDialog(null, "INCORRECT USERNAME OR PASSWORD", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Create the user_key table and insert test data
        createTableAndInsertTestData();

        // Launch the LoginFrame
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }

    // Method to create user_key table and insert test data
    private static void createTableAndInsertTestData() {
        // SQL statements to create table and insert data
        String createTableSQL = "CREATE TABLE IF NOT EXISTS user_key (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) NOT NULL," +
                "password VARCHAR(50) NOT NULL)";
        String insertDataSQL = "INSERT INTO user_key (username, password) VALUES " +
                "('admin', 'admin@123'), " +
                "('user1', 'password1'), " +
                "('user2', 'password2')";

        // Connection and statement objects
        Connection conn = null;
        Statement stmt = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();

            // Create table
            stmt.executeUpdate(createTableSQL);

            // Insert test data
            stmt.executeUpdate(insertDataSQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                // Close statement and connection
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Class for image background panel
    static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
