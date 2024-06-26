package Project.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Welcome extends JFrame implements ActionListener {

    private static final String ICON_PATH = "C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\";
    private static final String Wlcmbgpath = "C:\\Users\\AHTISHAM\\Desktop\\My_Workspace\\java_workspace\\Project\\icons\\wlcmbg.jpg";

    // Creating JButtons for different functionalities
    JButton PatientRegistration;
    JButton Billing;
    JButton Inventory;
    JButton Appointments;
    JLabel welcomelabel;

    Welcome() {
        // Setting properties of the Welcome frame
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        this.setSize(width - 20, height - 20);
        this.setTitle("Welcome To HMS");
        setIconImage(new ImageIcon(ICON_PATH + "hospital.png").getImage());
        this.setResizable(true);

        // Creating the background panel with an image
        WelcomeBackgroundPanel wlcmbgPanel = new WelcomeBackgroundPanel(new ImageIcon(Wlcmbgpath).getImage());
        wlcmbgPanel.setLayout(null);

        // Initializing label
        welcomelabel = new JLabel("     Welcome to LifeLine Medical Center");
        welcomelabel.setBackground(new Color(0xFF227756, true));
        welcomelabel.setForeground(Color.white);
        welcomelabel.setBounds(425, 50, 450, 50);
        welcomelabel.setFont(new Font("Calibri", 1, 25));
        welcomelabel.setVisible(true);
        welcomelabel.setOpaque(true);

        // Initializing buttons
        PatientRegistration = new JButton("Patient Registration");
        PatientRegistration.setBackground(new Color(0x18364B));
        PatientRegistration.setForeground(new Color(0xFEFEFF));
        PatientRegistration.setBounds(400, 250, 200, 50);
        PatientRegistration.setFont(new Font("Calibri", Font.BOLD, 16));
        PatientRegistration.setFocusable(false);
        PatientRegistration.addActionListener(this);

        Billing = new JButton("Billing");
        Billing.setBackground(new Color(0x8956CB));
        Billing.setForeground(new Color(0xFEFEFF));
        Billing.setBounds(700, 250, 200, 50);
        Billing.setFont(new Font("Calibri", Font.BOLD, 20));
        Billing.setFocusable(false);
        Billing.addActionListener(this);

        Inventory = new JButton("Inventory/Pharmacy");
        Inventory.setBackground(new Color(0xB48924));
        Inventory.setForeground(new Color(0xFEFEFF));
        Inventory.setBounds(400, 450, 200, 50);
        Inventory.setFont(new Font("Calibri", Font.BOLD, 20));
        Inventory.setFocusable(false);
        Inventory.addActionListener(this);

        Appointments = new JButton("Appointments");
        Appointments.setBackground(new Color(0x477521));
        Appointments.setForeground(new Color(0xFEFEFF));
        Appointments.setBounds(700, 450, 200, 50);
        Appointments.setFont(new Font("Calibri", Font.BOLD, 20));
        Appointments.setFocusable(false);
        Appointments.addActionListener(this);

        // Add buttons to the background panel
        wlcmbgPanel.add(welcomelabel);
        wlcmbgPanel.add(Appointments);
        wlcmbgPanel.add(PatientRegistration);
        wlcmbgPanel.add(Billing);
        wlcmbgPanel.add(Inventory);

        // Add the background panel to the frame
        this.setContentPane(wlcmbgPanel);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Welcome();
    }

    // When any button is pressed, action to be performed are added;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == PatientRegistration) {
            new RegistrationFrame(this);
        }

        if (e.getSource() == Inventory) {
            new InventoryFrame();
        }

        if (e.getSource() == Billing) {
            new billingFrame();
        }
        if (e.getSource() == Appointments) {
            new AppointmentsFrame();
        }
    }
}

// Class for image background panel
class WelcomeBackgroundPanel extends JPanel {
    private Image backgroundImage;

    public WelcomeBackgroundPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
