package OnlineSupermarketDelivery;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SignUpGUI implements ActionListener {

    ImageIcon logo;
    JFrame window;
    JPanel panelS;
    Font montserratFont;

    // Labels
    JLabel userNL, userPL, userPnL, fName, mName,
    lName, birthDate, gender, addressHome, fatherN, motherN,
    contactEmail, contactNumber, initialDep;
    // Fields
    JTextField userNF, fNameF, mNameF, lNameF,
    addressHomeF, motherNF, fatherNF, 
    contactEmailF, contactNumberF, initialDepF;

    JPasswordField userPF, userPnF;
    JButton submitButtn, cancelButtn;

    JComboBox<String> dayComboBox, monthComboBox, yearComboBox, genderDropdown;

    public SignUpGUI() {
        initializeWindow();
        initializePanel();
        addComponents();
        window.setVisible(true);
        
        // Center the window on the screen
        window.setLocationRelativeTo(null);
    }



    private void initializeWindow() {
        window = new JFrame();
        logo = new ImageIcon("ICO-Test2.png");
        window.setIconImage(logo.getImage());
        window.setTitle("SignUp Window");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setSize(900, 600);
    }

    private void initializePanel() {
        panelS = new GradientPanel();
        panelS.setLayout(null);
        window.add(panelS);
    }

    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(230, 190, 255); // Light purple color
            Color color2 = new Color(200, 160, 230); // Light purple color
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);

            // Draw stars
            drawStars(g2d, width, height);

            g2d.dispose();
        }

        private void drawStars(Graphics2D g2d, int width, int height) {
            g2d.setColor(new Color(245, 215, 245)); // Light purple-ish color
            for (int i = 0; i < 100; i++) {
                int x = (int) (Math.random() * width);
                int y = (int) (Math.random() * height);
                int size = (int) (Math.random() * 2) + 1;
                g2d.fillRect(x, y, size, size);
            }
        }
    }
    
    private void addComponents() {
        // Labels and Fields
        userNL = new JLabel("User Name:");
        userNL.setBounds(250, 20, 150, 30);
        panelS.add(userNL);

        userNF = new JTextField();
        userNF.setBounds(350, 20, 300, 30);
        panelS.add(userNF);

        userPL = new JLabel("Password:");
        userPL.setBounds(250, 60, 150, 30);
        panelS.add(userPL);

        userPF = new JPasswordField();
        userPF.setBounds(350, 60, 300, 30);
        panelS.add(userPF);

        fName = new JLabel("First Name:");
        fName.setBounds(250, 100, 150, 30);
        panelS.add(fName);

        fNameF = new JTextField();
        fNameF.setBounds(350, 100, 300, 30);
        panelS.add(fNameF);

        mName = new JLabel("Middle Name:");
        mName.setBounds(250, 140, 150, 30);
        panelS.add(mName);

        mNameF = new JTextField();
        mNameF.setBounds(350, 140, 300, 30);
        panelS.add(mNameF);

        lName = new JLabel("Last Name:");
        lName.setBounds(250, 180, 150, 30);
        panelS.add(lName);

        lNameF = new JTextField();
        lNameF.setBounds(350, 180, 300, 30);
        panelS.add(lNameF);

        birthDate = new JLabel("Date of Birth:");
        birthDate.setBounds(250, 220, 150, 30);
        panelS.add(birthDate);

        dayComboBox = new JComboBox<>(generateDays());
        dayComboBox.setBounds(350, 220, 70, 30);
        panelS.add(dayComboBox);

        monthComboBox = new JComboBox<>(new String[]{"Jan", "Feb", "Mar", "Apr", 
        "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
        monthComboBox.setBounds(420, 220, 90, 30);
        panelS.add(monthComboBox);

        yearComboBox = new JComboBox<>(generateYears(1920, 2023));
        yearComboBox.setBounds(511, 220, 90, 30);
        panelS.add(yearComboBox);

        gender = new JLabel("Gender:");
        gender.setBounds(250, 260, 150, 30);
        panelS.add(gender);

        genderDropdown = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderDropdown.setBounds(350, 260, 100, 30);
        panelS.add(genderDropdown);

        addressHome = new JLabel("Address:");
        addressHome.setBounds(250, 300, 150, 30);
        panelS.add(addressHome);

        addressHomeF = new JTextField();
        addressHomeF.setBounds(350, 300, 300, 30);
        panelS.add(addressHomeF);

        contactNumber = new JLabel("Contact Number:");
        contactNumber.setBounds(250, 340, 150, 30);
        panelS.add(contactNumber);

        contactNumberF = new JTextField();
        contactNumberF.setBounds(350, 340, 300, 30);
        panelS.add(contactNumberF);

        contactEmail = new JLabel("Contact Email:");
        contactEmail.setBounds(250, 380, 300, 30);
        panelS.add(contactEmail);

        contactEmailF = new JTextField();
        contactEmailF.setBounds(350, 380, 300, 30);
        panelS.add(contactEmailF);
        
        submitButtn = new JButton("Submit");
        submitButtn.setBounds(500, 500, 150, 30);
        panelS.add(submitButtn);
        submitButtn.addActionListener(this);

        cancelButtn = new JButton("Cancel");
        cancelButtn.setBounds(700, 500, 150, 30);
        cancelButtn.addActionListener(this);
        panelS.add(cancelButtn);
    }

    private String[] generateDays() {
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        return days;
    }

    private String[] generateYears(int startYear, int endYear) {
        int numberOfYears = endYear - startYear + 1;
        String[] years = new String[numberOfYears];
        for (int i = 0; i < numberOfYears; i++) {
            years[i] = String.valueOf(startYear + i);
        }
        return years;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButtn) {
            // Get user input
            String username, password, pin, firstName, middleName, lastName,
            dobDay, dobMonth, dobYear, gender, address,
            contactEmail, contactNumber;

            username = userNF.getText().trim();
            password = new String(userPF.getPassword());
            firstName = fNameF.getText().trim();
            middleName = mNameF.getText().trim();
            lastName = lNameF.getText().trim();
            dobDay = (String) dayComboBox.getSelectedItem();
            dobMonth = (String) monthComboBox.getSelectedItem();
            dobYear = (String) yearComboBox.getSelectedItem();
            gender = (String) genderDropdown.getSelectedItem();
            address = addressHomeF.getText().trim();
            contactEmail = contactEmailF.getText().trim();
            contactNumber = contactNumberF.getText().trim(); 

            // Validate required fields
            if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() ||
                    lastName.isEmpty() || dobDay == null || dobMonth == null || dobYear == null ||
                    gender == null || address.isEmpty() ||
                    contactEmail.isEmpty() || contactNumber.isEmpty()) {

                JOptionPane.showMessageDialog(null, "Please fill out all required fields", "Missing Information", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate the PIN as a 6-digit number
            if (!password.matches("\\d{6}")) {
                JOptionPane.showMessageDialog(null, "Password must be a 6-digit elements", "Invalid Password", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Continue with saving data if all validations pass
            int fileNumber = getNextFileNumber();
            String fileName = "user_details" + fileNumber + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)))) {
                // Write user data to file
                writer.write(username + "\n");
                writer.write(password + "\n");
                writer.write(firstName + "\n");
                writer.write(middleName + "\n");
                writer.write(lastName + "\n");
                writer.write(dobDay + "/" + dobMonth + "/" + dobYear + "\n");
                writer.write(gender + "\n");
                writer.write(address + "\n");
                writer.write(contactNumber + "\n");
                writer.write(contactEmail + "\n");

                JOptionPane.showMessageDialog(null, "Data submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                window.setVisible(false);
                JFrame WelcomePageGUI = new WelcomePageGUI();
                WelcomePageGUI.setVisible(true);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving data", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }

        cancelButtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancelButtn) {
                    window.setVisible(false); // Close the current window
                    WelcomePageGUI welcomePage = new WelcomePageGUI();
                    welcomePage.setVisible(true);
                }
            }
        });
    }

    private int getNextFileNumber() {
        int fileNumber = 1;
        while (new File("user_details" + fileNumber + ".txt").exists()) {
            fileNumber++;
        }
        return fileNumber;
    }

    // Clears all input fields after successful data submission.
    private void clearFields() {
        userNF.setText("");
        userPF.setText("");
        fNameF.setText("");
        mNameF.setText("");
        lNameF.setText("");
        addressHomeF.setText("");
        contactEmailF.setText("");
        contactNumberF.setText("");
    }
}