package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class Home extends JFrame implements ActionListener {

    JLabel loggedInUserLabel;

    public Home() {
        setLayout(new BorderLayout());
        setTitle("Air CSEDU Management System");
        setSize(1200, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sidebar panel
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 0, 0));
        sidebar.setBackground(new Color(25, 120, 220));

        // Add buttons 
        addSidebarButton(sidebar, "Flight Details", "View details about flights", this);
        addSidebarButton(sidebar, "Add Customer Details", "Add new customer information", this);
        addSidebarButton(sidebar, "Book Flight", "Book a flight for customers", this);
        addSidebarButton(sidebar, "Journey Details", "View journey details", this);
        addSidebarButton(sidebar, "Cancel Ticket", "Cancel a flight ticket", this);
        addSidebarButton(sidebar, "Boarding Pass", "Generate a boarding pass", this);

        
        add(sidebar, BorderLayout.WEST);

        
        JPanel topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setBackground(new Color(230, 230, 250));
        topBar.setPreferredSize(new Dimension(1200, 80));

        
        loggedInUserLabel = new JLabel("Logged in as: " + UserSession.loggedInUser, JLabel.RIGHT);
        loggedInUserLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        loggedInUserLabel.setForeground(new Color(70, 130, 180));
        loggedInUserLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        topBar.add(loggedInUserLabel, BorderLayout.CENTER);

        
        add(topBar, BorderLayout.NORTH);

        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Ahnaf\\Documents\\NetBeansProjects\\AirlineManagementSystem\\src\\airlinemanagementsystem\\icons\\asi.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        
        add(mainPanel, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(230, 230, 250));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Back 
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(new LineBorder(new Color(25, 120, 220), 1));
        backButton.setPreferredSize(new Dimension(120, 40));
        backButton.addActionListener(e -> {
            dispose();
            new Front();
        });
        bottomPanel.add(backButton);

        
        add(bottomPanel, BorderLayout.SOUTH);

      
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    private void addSidebarButton(JPanel sidebar, String text, String tooltip, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button.setBackground(new Color(30, 144, 255));
        button.setForeground(Color.WHITE);
        button.setToolTipText(tooltip);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(new Color(25, 120, 220), 1));
        button.addActionListener(listener);
        sidebar.add(button);
    }

    
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        ReservationAction action = null;

        if (command.equals("Flight Details")) {
            new FlightInfo();
        } else if (command.equals("Add Customer Details")) {
            new AddCustomer();
        } else if (command.equals("Book Flight")) {
            new BookFlight();
        } else if (command.equals("Journey Details")) {
            new JourneyDetails();
        } else if (command.equals("Cancel Ticket")) {
            action = new Cancel(); // Polymorphic assignment
        } else if (command.equals("Boarding Pass")) {
            action = new BoardingPass(); // Polymorphic assignment
        }

        
    }

    
    public static void main(String[] args) {
        UserSession.loggedInUser = "ahnaf123";
        new Home();
    }
}