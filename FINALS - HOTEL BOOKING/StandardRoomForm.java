package Finals;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class StandardRoomForm extends JFrame {
    private Font customFont;
    private JLabel hotelPhotoLabel; 

    public StandardRoomForm() {
        loadCustomFont();
        setTitle("STANDARD ROOM A DETAILS");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnNext = createStyledButton("Next", 150, 50);
        JButton btnBack = createStyledButton("Home", 150, 50);
        JButton btnBookNow = createStyledButton("Book Now", 150, 50); 

        btnNext.addActionListener(e -> {
            new StandardRoomForm2().setVisible(true);
            dispose();
        });
        btnBack.addActionListener(e -> {
            new HomeForm().setVisible(true);
            dispose();
        });
        btnBookNow.addActionListener(e -> {
            if (RoomBookingManager.bookRoom("Standard Room A")) {
                double price = 5000.0;
                new ReservationForm("Standard Room A", price).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(StandardRoomForm.this,
                        "Sorry, this room is not available. Can you book other room/s?",
                        "Booking Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(null);
        labelPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("STANDARD ROOM A");
        lblTitle.setFont(customFont.deriveFont(Font.BOLD, 40));
        lblTitle.setForeground(Color.BLACK);

        JLabel lblSubtitle = new JLabel("<html><body style='text-align: center; font-family: \"Sedan\", sans-serif;'>"
                + "<ul style='list-style-type: disc; padding-left: 20px; margin: 0 auto; text-align: left; list-style-position: inside;'>"
                + "<li style='padding: 2px; border-radius: 2px;'>₱ 5,000.00 per night</li>"
                + "<li style='padding: 2px; border-radius: 2px;'>1 Queen Size Bed with AC</li>"
                + "<li style='padding: 2px; border-radius: 2px;'>Complimentary Breakfast for 2</li>"
                + "<li style='padding: 2px; border-radius: 2px;'>Mini Ref and Water Heater</li>"
                + "<li style='padding: 2px; border-radius: 2px;'>Free WiFi Access</li>"
                + "<li style='padding: 2px; border-radius: 2px;'>24/7 Front Desk</li>"
                + "</ul></body></html>");
        lblSubtitle.setFont(customFont.deriveFont(Font.BOLD, 12));
        lblSubtitle.setForeground(Color.WHITE);
        lblSubtitle.setOpaque(true);
        lblSubtitle.setBackground(new Color(0, 0, 0, 150));

        lblTitle.setBounds(90, 20, 600, 50);
        lblSubtitle.setBounds(300, 250, 250, 120);

        hotelPhotoLabel = new JLabel();
        hotelPhotoLabel.setBounds(50, 80, 300, 150);
        labelPanel.add(hotelPhotoLabel); 

        labelPanel.add(lblTitle);
        labelPanel.add(lblSubtitle);

        SemiTransparentBackgroundPanel semiTransparentPanel = new SemiTransparentBackgroundPanel(new Color(255, 255, 255, 150));
        semiTransparentPanel.setLayout(new BoxLayout(semiTransparentPanel, BoxLayout.Y_AXIS));
        semiTransparentPanel.setBounds(50, 20, 600, 420);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnBack);
        buttonPanel.add(btnBookNow);

        JLayeredPane mainPanel = new JLayeredPane();
        mainPanel.setLayout(null);

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setBounds(0, 0, 700, 500);
        mainPanel.add(gradientPanel, new Integer(0)); 

        semiTransparentPanel.add(labelPanel);
        mainPanel.add(semiTransparentPanel, new Integer(1)); 

        buttonPanel.setBounds(0, 400, 700, 100); 
        mainPanel.add(buttonPanel, new Integer(2)); 

        add(mainPanel);
        
        setHotelPhoto("/standard.jpg");
    }

    public void setHotelPhoto(String imagePath) {
        try {
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                BufferedImage image = ImageIO.read(imageUrl);
                ImageIcon icon = new ImageIcon(image.getScaledInstance(300, 150, Image.SCALE_SMOOTH));
                hotelPhotoLabel.setIcon(icon);
            } else {
                throw new RuntimeException("Image not found: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Image Error", JOptionPane.ERROR_MESSAGE);
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

    private JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(new Color(30, 30, 30, 200));
        button.setFont(customFont.deriveFont(Font.BOLD, 14));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 100, 100, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 30, 30, 200));
            }
        });

        return button;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StandardRoomForm().setVisible(true));
    }
}
