package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField id;
    JButton show, copyPnrButton;

    public JourneyDetails() {
        getContentPane().setBackground(new Color(30, 30, 30)); 
        setLayout(null);

        JLabel title = new JLabel("Journey Details");
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(Color.YELLOW);
        title.setBounds(300, 10, 300, 30);
        add(title);

        JLabel lblpnr = new JLabel("National ID:");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblpnr.setForeground(Color.WHITE);
        lblpnr.setBounds(50, 60, 150, 25);
        add(lblpnr);

        id = new JTextField();
        id.setFont(new Font("Tahoma", Font.PLAIN, 16));
        id.setBounds(200, 60, 250, 30);
        add(id);

        show = new JButton("Show Details");
        show.setFont(new Font("Tahoma", Font.PLAIN, 16));
        show.setBackground(new Color(70, 130, 180)); // Steel Blue
        show.setForeground(Color.WHITE);
        show.setBounds(470, 60, 150, 30);
        show.addActionListener(this);
        add(show);

        copyPnrButton = new JButton("Copy PNR");
        copyPnrButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        copyPnrButton.setBackground(new Color(70, 130, 180)); // Steel Blue
        copyPnrButton.setForeground(Color.WHITE);
        copyPnrButton.setBounds(630, 60, 120, 30);
        copyPnrButton.addActionListener(this);
        add(copyPnrButton);

        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25); 
        table.setBackground(new Color(240, 240, 240)); 
        table.setForeground(Color.BLACK);

        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        table.setCellSelectionEnabled(true); 
        table.setAutoCreateRowSorter(true); 

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(50, 120, 700, 300);
        jsp.setBackground(Color.BLACK);
        add(jsp);

        setResizable(false);
        setSize(800, 500);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == show) {
            String nationalId = id.getText().trim(); 
            if (nationalId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a National ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String loggedInUser = UserSession.loggedInUser;

            try {
                Conn conn = new Conn();
                String query = "SELECT * FROM reservation WHERE id = '" + nationalId + "' AND added_by = '" + loggedInUser + "'";
                ResultSet rs = conn.s.executeQuery(query);

                if (!rs.isBeforeFirst()) { 
                    JOptionPane.showMessageDialog(null, "No Information Found or You are not authorized to view this data.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == copyPnrButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row to copy the PNR.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            String pnr = table.getValueAt(selectedRow, 0).toString();

            
            java.awt.datatransfer.StringSelection stringSelection = new java.awt.datatransfer.StringSelection(pnr);
            java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            
            JOptionPane.showMessageDialog(null, "Copied PNR: " + pnr, "Copy PNR", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        UserSession.loggedInUser = "ahnaf"; 
        new JourneyDetails();
    }
}