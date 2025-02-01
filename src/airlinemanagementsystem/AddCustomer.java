package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfname, tfphone, tfid, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;
    JLabel imageLabel; 
    JButton chooseImageButton;
    String imageName = null; 
    JLabel loggedInUserLabel; 

    public AddCustomer() {
        setTitle("Add Customer");
        getContentPane().setBackground(Color.BLACK);
        setSize(1000, 750);
        setLayout(null);
        setLocationRelativeTo(null);

        loggedInUserLabel = new JLabel("Logged in as: " + UserSession.loggedInUser);
        loggedInUserLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loggedInUserLabel.setForeground(Color.YELLOW);
        loggedInUserLabel.setBounds(800, 20, 200, 25);
        add(loggedInUserLabel);

        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(280, 20, 800, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.YELLOW);
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblname.setForeground(Color.YELLOW);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(220, 80, 250, 25);
        add(tfname);

        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 130, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblnationality.setForeground(Color.YELLOW);
        add(lblnationality);

        tfnationality = new JTextField();
        tfnationality.setBounds(220, 130, 250, 25);
        add(tfnationality);

        JLabel lblid = new JLabel("National ID");
        lblid.setBounds(60, 180, 150, 25);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblid.setForeground(Color.YELLOW);
        add(lblid);

        tfid = new JTextField();
        tfid.setBounds(220, 180, 250, 25);
        add(tfid);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lbladdress.setForeground(Color.YELLOW);
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 230, 250, 25);
        add(tfaddress);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 295, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblgender.setForeground(Color.YELLOW);
        add(lblgender);

        ButtonGroup gendergroup = new ButtonGroup();

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(220, 295, 70, 25);
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rbmale.setForeground(Color.YELLOW);
        rbmale.setBackground(Color.BLACK);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(330, 295, 100, 25);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 20));
        rbfemale.setForeground(Color.YELLOW);
        rbfemale.setBackground(Color.BLACK);
        add(rbfemale);

        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(60, 355, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblphone.setForeground(Color.YELLOW);
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 355, 250, 25);
        add(tfphone);

        JButton save = new JButton("SAVE");
        save.setBackground(Color.GRAY);
        save.setForeground(Color.WHITE);
        save.setBounds(380, 450, 150, 30);
        save.setFont(new Font("Tahoma", Font.PLAIN, 25));
        save.addActionListener(this);
        add(save);

        // Add Image Section
        chooseImageButton = new JButton("Choose Image");
        chooseImageButton.setBounds(670, 350, 150, 30);
        chooseImageButton.setBackground(Color.GRAY);
        chooseImageButton.setForeground(Color.WHITE);
        chooseImageButton.addActionListener(e -> selectImage());
        add(chooseImageButton);

        imageLabel = new JLabel();
        imageLabel.setBounds(670, 80, 150, 150);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
        add(imageLabel);

        setSize(1000, 750);
        setResizable(false);
       // setLocation(300, 100);
        setVisible(true);
    }

    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Avatar Image");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imageName = selectedFile.getName(); // Extract only the image name

            // Define the target directory to save the image
            File targetDir = new File("C:\\MyApp\\Images");
            if (!targetDir.exists()) {
                targetDir.mkdirs(); // Create the directory if it doesn't exist
            }

            // Move the image to the target directory
            File targetFile = new File(targetDir, imageName);
            try {
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving the image!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Display the image
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(targetFile.getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            imageLabel.setIcon(imageIcon);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String phone = tfphone.getText();
        String address = tfaddress.getText();
        String id = tfid.getText();
        String gender = rbmale.isSelected() ? "Male" : "Female";
        String loggedInUser = UserSession.loggedInUser;

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO passenger1 (name, nationality, phone, address, id, gender, avatar, added_by) VALUES ('" 
                            + name + "', '" + nationality + "', '" + phone + "', '" + address + "', '" + id + "', '" 
                            + gender + "', '" + imageName + "', '" + loggedInUser + "')";

            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");

            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}