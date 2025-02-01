
package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MakePayment extends JFrame implements ActionListener {
    JRadioButton Visa, Master;
    JTextField cardNumber, cvn;
    JComboBox<String> expMonth, expYear;
    JButton pay, cancel;
    JLabel lblTotalAmount;
    String pnr;

    public MakePayment(String pnr) {
        this.pnr = pnr;

        setTitle("Payment Gateway");
        setLayout(null);

        JLabel lblTitle = new JLabel("Payment Details");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(50, 10, 200, 40);
        lblTitle.setForeground(new Color(0, 102, 102));
        add(lblTitle);

        JLabel lblCardType = new JLabel("Card Type:");
        lblCardType.setBounds(50, 70, 100, 25);
        add(lblCardType);
        
        ButtonGroup cardgroup = new ButtonGroup();

        Visa = new JRadioButton("Visa");
        Visa.setBounds(200, 70, 70, 25);
        Visa.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Visa.setForeground(Color.BLACK);
        Visa.setFocusPainted(false);
        //Visa.setBackground(Color.BLACK);
        add(Visa);

        Master = new JRadioButton("Master");
        Master.setBounds(300, 70, 100, 25);
        Master.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Master.setForeground(Color.BLACK);
        Master.setFocusPainted(false);
        //Master.setBackground(Color.BLACK);
        add(Master);

        cardgroup.add(Visa);
        cardgroup.add(Master);


        JLabel lblCardNumber = new JLabel("Card Number:");
        lblCardNumber.setBounds(50, 110, 100, 25);
        add(lblCardNumber);

        cardNumber = new JTextField();
        cardNumber.setBounds(180, 110, 150, 25);
        add(cardNumber);

        JLabel lblExpMonth = new JLabel("Exp Month:");
        lblExpMonth.setBounds(50, 150, 100, 25);
        add(lblExpMonth);

        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        expMonth = new JComboBox<>(months);
        expMonth.setBounds(180, 150, 70, 25);
        add(expMonth);

        JLabel lblExpYear = new JLabel("Exp Year:");
        lblExpYear.setBounds(305, 150, 100, 25);
        add(lblExpYear);

        String[] years = {"2024", "2025", "2026", "2027", "2028", "2029", "2030"};
        expYear = new JComboBox<>(years);
        expYear.setBounds(375, 150, 70, 25);
        add(expYear);

        JLabel lblCVN = new JLabel("CVN:");
        lblCVN.setBounds(50, 190, 100, 25);
        add(lblCVN);

        cvn = new JTextField();
        cvn.setBounds(180, 190, 80, 25);
        add(cvn);

        pay = new JButton("Pay");
        pay.setBounds(360, 250, 80, 25);
        pay.setBackground(new Color(0, 102, 102));
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        pay.setFocusPainted(false);
        add(pay);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 250, 80, 25);
        cancel.addActionListener(this);
        cancel.setFocusPainted(false);
        add(cancel);

       

        setSize(530, 340);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            String enteredCardNumber = cardNumber.getText();
            String enteredCVN = cvn.getText();

            if (enteredCardNumber.isEmpty() || enteredCVN.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter all details!");
                return;
            }

            if (enteredCardNumber.length() < 16 || enteredCVN.length() != 3) {
                JOptionPane.showMessageDialog(null, "Invalid card details!");
                return;
            }
            JOptionPane.showMessageDialog(null, "Payment Successful!\nYour PNR: " + pnr);
           
            setVisible(false);
        }

        if (ae.getSource() == cancel) {
            new BookFlight();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new MakePayment("Not generated.");
    }
}