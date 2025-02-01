package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfid;
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;
    JButton bookflight, fetchButton, flight;
    Choice source, destination, classChoice;  
    JDateChooser dcdate;
    JLabel loggedInUserLabel, avatarLabel;

    public BookFlight() {
        setTitle("Book Flight");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Add a label to show the logged-in username at the top-right
        loggedInUserLabel = new JLabel("Logged in as: " + UserSession.loggedInUser);
        loggedInUserLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loggedInUserLabel.setForeground(Color.BLUE);
        loggedInUserLabel.setBounds(700, 20, 200, 25); // Position it at the top-right corner
        add(loggedInUserLabel);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(350, 20, 500, 35);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 32));
        heading.setForeground(new Color(0, 102, 102));
        add(heading);

        JLabel lblid = new JLabel("National ID");
        lblid.setBounds(60, 130, 150, 25);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblid);

        tfid = new JTextField();
        tfid.setBounds(220, 130, 150, 25);
        add(tfid);

        fetchButton = new JButton("Search");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 130, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        
        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 180, 150, 25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsource);

        source = new Choice();
        source.setBounds(220, 180, 150, 25);
        add(source);

        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60, 230, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);

        destination = new Choice();
        destination.setBounds(220, 230, 150, 25);
        add(destination);

        // Added class choice dropdown
        JLabel lblclass = new JLabel("Class");
        lblclass.setBounds(60, 280, 150, 25);
        lblclass.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblclass);

        classChoice = new Choice();
        classChoice.setBounds(220, 280, 150, 25);
        classChoice.add("Business Class");
        classChoice.add("Economy Class");
        classChoice.add("First Class");
        add(classChoice);

        try {
            Conn c = new Conn();
            String query = "SELECT * FROM flight";
            ResultSet rs = c.s.executeQuery(query);

            while(rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        flight = new JButton("Search Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380, 280, 120, 25);
        flight.addActionListener(this);
        add(flight);

        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 330, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfname);

        labelfname = new JLabel();
        labelfname.setBounds(220, 330, 150, 25);
        add(labelfname);

        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 380, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);

        labelfcode = new JLabel();
        labelfcode.setBounds(220, 380, 150, 25);
        add(labelfcode);

        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60, 430, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(220, 430, 150, 25);
        add(dcdate);

        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(220, 480, 150, 25);
        bookflight.addActionListener(this);
        add(bookflight);
        
        JLabel lbldetails = new JLabel("Details:");
        lbldetails.setBounds(690, 95, 150, 25);
        lbldetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lbldetails);
        
        JLabel lblimage = new JLabel("Image");
        lblimage.setBounds(695, 240, 150, 25);
        lblimage.setFont(new Font("Tahoma", Font.PLAIN, 12));
        add(lblimage);
        
        avatarLabel = new JLabel();
        avatarLabel.setBounds(650, 120, 125, 125); 
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1)); 
        add(avatarLabel);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(580, 280, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(720, 280, 150, 25);
        add(tfname);

        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(580, 330, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);

        tfnationality = new JLabel();
        tfnationality.setBounds(720, 330, 150, 25);
        add(tfnationality);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(580, 380, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        tfaddress = new JLabel();
        tfaddress.setBounds(720, 380, 150, 25);
        add(tfaddress);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(580, 430, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        labelgender = new JLabel();
        labelgender.setBounds(720, 430, 150, 25);
        add(labelgender);

        setSize(900, 600);
        setLocation(270, 70);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String id = tfid.getText();
            String loggedInUser = UserSession.loggedInUser;

            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM passenger1 WHERE id = '" + id + "' AND added_by = '" + loggedInUser + "'";

                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                    //tfimagename.setText(rs.getString("avatar"));
                    
                    String imageName = rs.getString("avatar");
                    String imagePath = "C:\\MyApp\\Images\\" + imageName;
                    if (imagePath != null && !imagePath.isEmpty()) {
                        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH));
                        avatarLabel.setIcon(imageIcon);
                       
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No matching passenger found for your account.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM flight WHERE source = '" + src + "' AND destination = '" + dest + "'";

                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    labelfname.setText(rs.getString("f_name"));
                    labelfcode.setText(rs.getString("f_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "No Flights Found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == bookflight) {
            // Validate required fields
            String id = tfid.getText();
            String name = tfname.getText();
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText();
            String flightcode = labelfcode.getText();
            String src = source.getSelectedItem();
            String des = destination.getSelectedItem();
            String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            String flightClass = classChoice.getSelectedItem();  // Get the selected flight class
            String loggedInUser = UserSession.loggedInUser;

            if (id.isEmpty() || name.isEmpty() || nationality.isEmpty() || flightname.isEmpty() || flightcode.isEmpty() || src.isEmpty() || des.isEmpty() || ddate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }
            Random random = new Random();
            String pnr = "PNR-" + random.nextInt(1000000);

            try {
                //Random random = new Random();
                Conn conn = new Conn();

                // Updated query to include class
                String query = "INSERT INTO reservation (PNR, TIC, id, name, nationality, flightname, flightcode, src, des, ddate, class, added_by) VALUES ('" +
                               pnr + "', '" +
                               "TIC-" + random.nextInt(10000) + "', '" +
                               id + "', '" +
                               name + "', '" +
                               nationality + "', '" +
                               flightname + "', '" +
                               flightcode + "', '" +
                               src + "', '" +
                               des + "', '" +
                               ddate + "', '" +
                               flightClass + "', '" +
                               loggedInUser + "')";
                // Debugging: Print query to ensure it's correct
                System.out.println("Executing query: " + query);

                conn.s.executeUpdate(query);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error booking the flight.");
            }
            new MakePayment(pnr);
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}