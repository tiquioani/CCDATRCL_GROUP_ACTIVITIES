package Finals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class ReservationForm extends JFrame {

    private JTextField txtName, txtContact, txtCheckIn, txtCheckOut, txtEmail;
    private JComboBox<String> cmbPayment;
    private String roomDetails;
    private double price;
    private Font customFont;

    public ReservationForm(String roomDetails, double price) {
        this.roomDetails = roomDetails;
        this.price = price;

        setTitle("RESERVATION FORM - Aurum Manila");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loadCustomFont();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        JLabel lblName = new JLabel("Full Name:");
        JLabel lblContact = new JLabel("Contact Number:");
        JLabel lblCheckIn = new JLabel("Check-in (M-D-Y):"); 
        JLabel lblCheckOut = new JLabel("Check-out (M-D-Y):"); 
        JLabel lblEmail = new JLabel("Email Address:");
        JLabel lblPayment = new JLabel("Payment Method:");
        JLabel lblPrice = new JLabel("Price: P " + price + " per night");

        int labelFontSize = 13;
        Color labelColor = Color.WHITE;

        lblName.setFont(customFont.deriveFont(Font.BOLD, labelFontSize));
        lblName.setForeground(labelColor);
        lblContact.setFont(customFont.deriveFont(Font.BOLD, labelFontSize));
        lblContact.setForeground(labelColor);
        lblCheckIn.setFont(customFont.deriveFont(Font.BOLD, labelFontSize));
        lblCheckIn.setForeground(labelColor);
        lblCheckOut.setFont(customFont.deriveFont(Font.BOLD, labelFontSize));
        lblCheckOut.setForeground(labelColor);
        lblEmail.setFont(customFont.deriveFont(Font.BOLD, labelFontSize));
        lblEmail.setForeground(labelColor);
        lblPayment.setFont(customFont.deriveFont(Font.BOLD, labelFontSize));
        lblPayment.setForeground(labelColor);
        lblPrice.setFont(customFont.deriveFont(Font.BOLD, labelFontSize));
        lblPrice.setForeground(labelColor);

        txtName = new JTextField();
        txtContact = new JTextField("+63");
        txtCheckIn = new JTextField(); 
        txtCheckOut = new JTextField(); 
        txtEmail = new JTextField();

        String[] paymentMethods = {"Credit Card", "Debit Card", "Cash"};
        cmbPayment = new JComboBox<>(paymentMethods);

        JButton btnSubmit = new JButton("Submit");
        styleButton(btnSubmit);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText().trim();
                String contact = txtContact.getText().trim();
                String checkInText = txtCheckIn.getText().trim(); 
                String checkOutText = txtCheckOut.getText().trim(); 
                String email = txtEmail.getText().trim();
                String payment = (String) cmbPayment.getSelectedItem().toString();
                

                if (name.isEmpty() || contact.isEmpty() || checkInText.isEmpty() || checkOutText.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!contact.matches("\\+63\\d+")) {
                    JOptionPane.showMessageDialog(null, "Contact number must start with +63 and contain only digits after +63.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate check-in and check-out dates
                LocalDate checkInDate;
                LocalDate checkOutDate;
                try {
                    checkInDate = LocalDate.parse(checkInText, formatter);
                    checkOutDate = LocalDate.parse(checkOutText, formatter);

                    if (checkOutDate.isBefore(checkInDate)) {
                        throw new IllegalArgumentException("Check-out date must be after check-in date.");
                    }
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid dates in MM-DD-YYYY format.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long daysOfStay = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
                if (daysOfStay <= 0) {
                    JOptionPane.showMessageDialog(null, "Check-out date must be at least one day after the check-in date.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double totalPrice = daysOfStay * price;

                new ConfirmationForm(name, contact, email, payment, roomDetails, checkInDate, checkOutDate, LocalDateTime.now()).setVisible(true);
                dispose();
            }
        });

        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 100, 100, 200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(30, 30, 30, 200)); 
            }
        });

        JButton btnBack = new JButton("Back");
        styleButton(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeForm().setVisible(true);
                dispose();
            }
        });
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBack.setBackground(new Color(76, 92, 104)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBack.setBackground(new Color(93, 109, 126)); 
            }
        });

        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setOpaque(false);
        formPanel.setBounds(50, 50, 600, 300);

        lblName.setBounds(50, 5, 100, 30);
        txtName.setBounds(200, 5, 300, 30);
        lblContact.setBounds(50, 45, 120, 30);
        txtContact.setBounds(200, 45, 300, 30);
        lblCheckIn.setBounds(50, 85, 150, 30);
        txtCheckIn.setBounds(200, 85, 300, 30);
        lblCheckOut.setBounds(50, 125, 150, 30);
        txtCheckOut.setBounds(200, 125, 300, 30);
        lblEmail.setBounds(50, 165, 100, 30);
        txtEmail.setBounds(200, 165, 300, 30);
        lblPayment.setBounds(50, 205, 120, 30);
        cmbPayment.setBounds(200, 205, 300, 30);
        lblPrice.setBounds(200, 245, 200, 30);

        formPanel.add(lblName);
        formPanel.add(txtName);
        formPanel.add(lblContact);
        formPanel.add(txtContact);
        formPanel.add(lblCheckIn);
        formPanel.add(txtCheckIn);
        formPanel.add(lblCheckOut);
        formPanel.add(txtCheckOut);
        formPanel.add(lblEmail);
        formPanel.add(txtEmail);
        formPanel.add(lblPayment);
        formPanel.add(cmbPayment);
        formPanel.add(lblPrice);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(50, 400, 600, 60);

        btnSubmit.setBounds(100, 10, 150, 40);
        btnBack.setBounds(350, 10, 150, 40);
        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnBack);

        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 700, 500);

        mainPanel.add(formPanel);
        mainPanel.add(buttonPanel);

        add(mainPanel);
    }

    private void loadCustomFont() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Sedan-Regular.ttf");
            if (fontStream != null) {
                customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN, 18);
            } else {
                System.err.println("Font not found.");
                customFont = new Font("Arial", Font.PLAIN, 18);
            }
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 18);
        }
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(150, 60));
        button.setFont(customFont.deriveFont(Font.BOLD, 16));
        button.setBackground(new Color(30, 30, 30, 200));
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
    }

    class GradientPanel extends JPanel {
        private Image backgroundImage;

        public GradientPanel() {
            try {
                URL imageUrl = getClass().getResource("/Aurum Manila.jpg");
                if (imageUrl != null) {
                    backgroundImage = Toolkit.getDefaultToolkit().getImage(imageUrl);
                } else {
                    System.err.println("Image not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservationForm("Deluxe Room", 5000).setVisible(true));
    }
}

