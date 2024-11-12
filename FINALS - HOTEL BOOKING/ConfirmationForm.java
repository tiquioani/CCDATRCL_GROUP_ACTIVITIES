package Finals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ConfirmationForm extends JFrame {
    private static Font customFont;
    private String transactionNumber;
    private int counter = 1;
    private static final String PREFIX = "AU-C";

    public ConfirmationForm(String name, String contact, String email, String payment, String roomDetails,
                            LocalDate checkInDate, LocalDate checkOutDate, LocalDateTime bookingTime) {
        loadCustomFont();
        setTitle("BOOKING CONFIRMATION - Aurum Manila");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        transactionNumber = generateUniqueTransactionNumber();

        double price = getRoomPrice(roomDetails);
        long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        if (days <= 0) {
            days = 1; 
        }
      
        double totalPrice = days * price;

        writeTransactionToFile(name, roomDetails, checkInDate, checkOutDate, totalPrice, transactionNumber);
        
        SemiTransparentBackgroundPanel semiTransparentPanel = new SemiTransparentBackgroundPanel(new Color(0, 0, 0, 150));
        semiTransparentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        Customer newCustomer = new Customer(name, contact, days, email, payment, roomDetails, price, checkInDate, checkOutDate, transactionNumber);
        CustomerManager.addCustomer(newCustomer); // Ensure this is called
        
        JLabel confirmationLabel = new JLabel();
        confirmationLabel.setText("<html><div style='text-align: center;'>Thank you for your reservation! <br><br>" +
                "Transaction Number: " + transactionNumber + "<br>" +
                "Name: " + name + "<br>" +
                "Room Type: " + roomDetails + "<br>" +
                "Check-in Date: " + checkInDate.format(formatter) + "<br>" +
                "Check-out Date: " + checkOutDate.format(formatter) + "<br>" +
                "Total Price: P " + totalPrice + "<br></div></html>");
        confirmationLabel.setFont(customFont.deriveFont(Font.PLAIN, 14));
        confirmationLabel.setForeground(Color.WHITE);
        confirmationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        semiTransparentPanel.add(confirmationLabel, gbc);

        JButton btnOkay = new JButton("Okay");
        btnOkay.setPreferredSize(new Dimension(150, 60));
        btnOkay.setFont(customFont.deriveFont(Font.BOLD, 15));
        btnOkay.setBackground(new Color(0, 0, 0, 128)); 
        btnOkay.setForeground(Color.WHITE);
        btnOkay.setFocusPainted(false);
        btnOkay.setBorder(BorderFactory.createEmptyBorder());

        btnOkay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                SwingUtilities.invokeLater(() -> new WelcomeForm().setVisible(true));
            }
        });

        gbc.gridy = 1;
        semiTransparentPanel.add(btnOkay, gbc);

        JLayeredPane mainPanel = new JLayeredPane();
        mainPanel.setLayout(null);

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setBounds(0, 0, 700, 500);
        mainPanel.add(gradientPanel, new Integer(0));

        semiTransparentPanel.setBounds(150, 20, 400, 400); 
        mainPanel.add(semiTransparentPanel, new Integer(1));

        add(mainPanel);
    }

    private String generateUniqueTransactionNumber() {
        String transactionNumber;
        do {
            transactionNumber = PREFIX + String.format("%03d", counter++);
        } while (transactionExists(transactionNumber));
        return transactionNumber;
    }

    private boolean transactionExists(String transactionNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(transactionNumber)) {
                    return true; 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void writeTransactionToFile(String name, String roomDetails, LocalDate checkInDate, LocalDate checkOutDate, double totalPrice, String transactionNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader("customers.txt"))) {
            String line;
            boolean exists = false;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && 
                    parts[1].equals(name) &&
                    parts[2].equals(roomDetails) &&
                    parts[3].equals(checkInDate.toString()) &&
                    parts[4].equals(checkOutDate.toString())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("customers.txt", true))) {
                    writer.write(transactionNumber);
                    writer.write("," + name);
                    writer.write("," + roomDetails);
                    writer.write("," + checkInDate);
                    writer.write("," + checkOutDate);
                    writer.write("," + totalPrice);
                    writer.newLine(); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double getRoomPrice(String roomDetails) {
        switch (roomDetails) {
            case "Standard Room A":
            case "Standard Room B":
            case "Standard Room C":
                return 5000.0;
            case "Twin Room A":
            case "Twin Room B":
            case "Twin Room C":
                return 7500.0;
            case "Family Room A":
            case "Family Room B":
            case "Family Room C":
                return 10000.0;
            case "Promo Room A":
            case "Promo Room B":
            case "Promo Room C":
                return 3000.0;
            default:
                throw new IllegalArgumentException("Unknown room type: " + roomDetails);
        }
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

    private void loadCustomFont() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Sedan-Regular.ttf");
            if (fontStream != null) {
                customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.PLAIN, 18);
            } else {
                System.err.println("Font not found.");
                customFont = new Font("Courier New", Font.PLAIN, 18);
            }
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Courier New", Font.PLAIN, 18);
        }
    }

    class SemiTransparentBackgroundPanel extends JPanel {
        private Color backgroundColor;

        public SemiTransparentBackgroundPanel(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(backgroundColor);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
