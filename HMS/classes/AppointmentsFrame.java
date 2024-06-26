package Project.classes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AppointmentsFrame extends JFrame {
    private static final String ICON_PATH = "C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\hospital.png";
    private static final String BG_PATH = "C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\regbbg.png";

    private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private JTable appointmentsTable;
    private JScrollPane scrollPane;
    private JButton printTokenButton;
    private JButton closeButton;

    public AppointmentsFrame() {
        setTitle("Appointments");
        setIconImage(new ImageIcon(ICON_PATH).getImage());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose on close, not exit
        setLayout(new BorderLayout());

        // Create background panel
        BackgroundPanel bgPanel = new BackgroundPanel(new ImageIcon(BG_PATH).getImage());
        bgPanel.setLayout(new BorderLayout());

        // Fetch appointments from database
        fetchAppointmentsFromDatabase();

        // Add scroll pane with table to background panel
        bgPanel.add(scrollPane, BorderLayout.CENTER);

        // Add buttons panel to the bottom
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components

        printTokenButton = new JButton("Print Token");
        printTokenButton.setFont(new Font("Arial", Font.BOLD, 14));
        printTokenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printSelectedAppointmentToken();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonsPanel.add(printTokenButton, gbc);

        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close only AppointmentsFrame
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonsPanel.add(closeButton, gbc);

        bgPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Add background panel to frame
        add(bgPanel);

        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set frame to full screen
        setVisible(true);
    }

    private void fetchAppointmentsFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM appointments";
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Create JTable with ResultSet data
                appointmentsTable = new JTable(buildTableModel(resultSet));
                appointmentsTable.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for table
                appointmentsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Set font for table header
                appointmentsTable.setRowHeight(30); // Set row height
                scrollPane = new JScrollPane(appointmentsTable);
                scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border around scroll pane
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching appointments: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to convert ResultSet to TableModel
    private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Get column names
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            columnNames[columnIndex - 1] = metaData.getColumnName(columnIndex);
        }

        // Get data rows
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        while (rs.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                rowData[i] = rs.getObject(i + 1);
            }
            tableModel.addRow(rowData);
        }

        return tableModel;
    }

    private void printSelectedAppointmentToken() {
        int selectedRow = appointmentsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an appointment to print the token.", "No Appointment Selected", JOptionPane.WARNING_MESSAGE);
        } else {
            String patientName = (String) appointmentsTable.getValueAt(selectedRow, 1);
            java.sql.Date appointmentDate = (java.sql.Date) appointmentsTable.getValueAt(selectedRow, 3); // Correctly cast to java.sql.Date
            String formattedDate = appointmentDate.toString(); // Format the date as needed
            String token = generateToken(patientName, formattedDate);
            JOptionPane.showMessageDialog(this, "Token: " + token, "Appointment Token", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String generateToken(String patientName, String appointmentDate) {
        // Dummy token generation logic, replace with your actual token generation logic
        return patientName.substring(0, 3).toUpperCase() + "-" + appointmentDate.replaceAll("-", "");
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
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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

        // Launch the appointments frame
        SwingUtilities.invokeLater(() -> new AppointmentsFrame());
    }
}
