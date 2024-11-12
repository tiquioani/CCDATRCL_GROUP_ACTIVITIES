package Finals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;

public class FamilyRoomForm extends JFrame {
    private Font customFont;
    private JLabel hotelPhotoLabel;
    

    public FamilyRoomForm() {
        loadCustomFont();
        setTitle("FAMILY ROOM A DETAILS");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnNext = createStyledButton("Next", 150, 50);
        JButton btnBack = createStyledButton("Home", 150, 50);
        JButton btnBookNow = createStyledButton("Book Now", 150, 50); 

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FamilyRoomForm2().setVisible(true);
                dispose();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeForm().setVisible(true);
                dispose();
            }
        });

        btnBookNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RoomBookingManager.bookRoom("Family Room A")) {
                    
                    String roomDetails = "Family Room A";
                    double price = 10000;
                    new ReservationForm(roomDetails, price).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(FamilyRoomForm.this,
                            "Sorry, this room is not available. Please try booking another room.",
                            "Booking Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

       
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(null); 
        labelPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("FAMILY ROOM A");
        lblTitle.setFont(customFont.deriveFont(Font.BOLD, 40));
        lblTitle.setForeground(Color.BLACK);

        JLabel lblSubtitle = new JLabel("<html><body style='text-align: center; font-family: \"Sedan\", sans-serif;'>"
                + "<ul style='list-style-type: disc; padding-left: 20px; margin: 0 auto; text-align: left; list-style-position: inside;'>"
                + "<li style='padding: 2px;'>₱ 10,000.00 per night</li>"
                + "<li style='padding: 2px;'>2 Queen Size Beds with AC</li>"
                + "<li style='padding: 2px;'>Mini Sala</li>"
                + "<li style='padding: 2px;'>Complimentary Breakfast for 5</li>"
                + "<li style='padding: 2px;'>Mini Ref and Water Heater</li>"
                + "<li style='padding: 2px;'>Free WiFi Access</li>"
                + "<li style='padding: 2px;'>24/7 Front Desk</li>"
                + "</ul></body></html>");
        lblSubtitle.setFont(customFont.deriveFont(Font.BOLD, 12));
        lblSubtitle.setForeground(Color.WHITE);
        lblSubtitle.setOpaque(true);
        lblSubtitle.setBackground(new Color(0, 0, 0, 150));

        lblTitle.setBounds(120, 20, 600, 50);
        lblSubtitle.setBounds(320, 240, 230, 140);

        hotelPhotoLabel = new JLabel();
        hotelPhotoLabel.setBounds(50, 80, 300, 150);

        labelPanel.add(hotelPhotoLabel);
        labelPanel.add(lblTitle);
        labelPanel.add(lblSubtitle);
        labelPanel.setBounds(50, 20, 600, 400);

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
        setHotelPhoto("/froom1.jpg");
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
                customFont = new Font("Courier New", Font.PLAIN, 18);
            }
        } catch (Exception e) {
            customFont = new Font("Courier New", Font.PLAIN, 18);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FamilyRoomForm().setVisible(true));
    }
}
