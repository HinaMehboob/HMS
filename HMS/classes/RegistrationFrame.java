package Project.classes;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegistrationFrame extends JFrame {
    private static final String ICON_PATH = "C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\hospital.png";
    private static final String BG_PATH = "C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\regbbg.png";

    JTextField ageField, nameField, dateField;
    JLabel ageLabel, nameLabel, dateLabel, departmentLabel, bedVar, regFormLabel;
    JButton registrationButton, closeButton;
    JList<String> departmentList;
    Checkbox bedAllocation;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private JFrame welcomeFrame;

    public RegistrationFrame(JFrame welcomeFrame) {
        this.welcomeFrame = welcomeFrame;
        welcomeFrame.setVisible(false);

        // Set frame properties
        setTitle("Patient Registration");
        setIconImage(new ImageIcon(ICON_PATH).getImage());
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Set the frame to full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);

        // Creating a background panel
        BackgroundPanel bgPanel = new BackgroundPanel(new ImageIcon(BG_PATH).getImage());
        bgPanel.setLayout(null);
        bgPanel.setBounds(0, 0, screenSize.width, screenSize.height);

        // Initializing components

        // Fields
        ageField = new JTextField(20);
        nameField = new JTextField(20);
        dateField = new JTextField(20);

        // Labels
        ageLabel = new JLabel("Age");
        nameLabel = new JLabel("Patient Name");
        dateLabel = new JLabel("Appointment Date");
        departmentLabel = new JLabel("Department");
        bedVar = new JLabel("Bed Allocated");

        regFormLabel = new JLabel("Patient Registration Form");
        regFormLabel.setBackground(new Color(0x8956CB));
        regFormLabel.setOpaque(true);
        regFormLabel.setForeground(Color.white);
        ImageIcon icon = new ImageIcon("C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\registartion.png");
        regFormLabel.setIcon(icon);

        registrationButton = new JButton("Confirm Registration");
        registrationButton.setBackground(new Color(61, 100, 110));
        registrationButton.setForeground(Color.WHITE);
        registrationButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        registrationButton.setFocusable(false);
        registrationButton.addActionListener(e -> registrationButtonActionPerformed());

        closeButton = new JButton("Close");
        closeButton.setBackground(new Color(61, 100, 110));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        closeButton.setFocusable(false);
        closeButton.addActionListener(e -> {
            welcomeFrame.setVisible(true);
            dispose();
        });

        departmentList = new JList<>(new String[]{"Cardiology", "Neurology", "Orthopedics", "Pediatrics", "Oncology"});
        bedAllocation = new Checkbox("Check if Yes");

        // Adding components to panels
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setOpaque(false);
        formPanel.setBounds(50, 50, screenSize.width - 100, screenSize.height - 100);

        regFormLabel.setBounds(400, 20, 400, 50);
        regFormLabel.setFont(new Font("Monospace", 1, 24));
        formPanel.add(regFormLabel);

        nameLabel.setBounds(100, 100, 100, 30);
        nameField.setBounds(200, 100, 200, 30);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        ageLabel.setBounds(100, 200, 100, 30);
        ageField.setBounds(200, 200, 200, 30);
        formPanel.add(ageLabel);
        formPanel.add(ageField);

        dateLabel.setBounds(500, 200, 150, 30);
        dateField.setBounds(650, 200, 200, 30);
        formPanel.add(dateLabel);
        formPanel.add(dateField);

        departmentLabel.setBounds(100, 300, 100, 30);
        JScrollPane departmentScrollPane = new JScrollPane(departmentList);
        departmentScrollPane.setBounds(200, 300, 200, 50);
        formPanel.add(departmentLabel);
        formPanel.add(departmentScrollPane);

        bedVar.setBounds(500, 300, 100, 30);
        bedAllocation.setBounds(600, 300, 100, 30);
        formPanel.add(bedVar);
        formPanel.add(bedAllocation);

        registrationButton.setBounds(500, 400, 170, 40);
        closeButton.setBounds(500, 450, 170, 40);
        formPanel.add(registrationButton);
        formPanel.add(closeButton);

        bgPanel.add(formPanel);

        // Adding background panel to frame
        add(bgPanel);
        setVisible(true);
    }

    private void registrationButtonActionPerformed() {
        if (nameField.getText().trim().isEmpty() || ageField.getText().trim().isEmpty() || departmentList.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all details of Patient", "Details required", JOptionPane.WARNING_MESSAGE);
        } else {
            String patientName = nameField.getText().trim();
            String age = ageField.getText().trim();
            String date = dateField.getText().trim();
            Boolean bed = bedAllocation.getState();
            String department = departmentList.getSelectedValue();

            // Save patient details to database
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String query = "INSERT INTO appointments (patient_name, age, appointment_date, department, bed_allocated) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, patientName);
                    preparedStatement.setString(2, age);
                    preparedStatement.setString(3, date);
                    preparedStatement.setString(4, department);
                    preparedStatement.setBoolean(5, bed);

                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Registered Successfully", "Patient Details", JOptionPane.INFORMATION_MESSAGE);

                    // Open AppointmentsFrame
                    new AppointmentsFrame();

                    this.dispose();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error registering patient: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Load MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // Launch the registration frame
        new Welcome();
    }

    // Dummy class for background panel
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
}
