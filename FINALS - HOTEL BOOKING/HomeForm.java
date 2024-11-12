package Finals;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HomeForm extends JFrame {
    private Font customFont;

    public HomeForm() {
        loadCustomFont(); 
        setTitle("HOME - Aurum Manila");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null); 

        SemiTransparentBackgroundPanel semiTransparentPanel = new SemiTransparentBackgroundPanel(new Color(255, 255, 255, 150));
        semiTransparentPanel.setBounds(50, 20, 600, 420); 
        semiTransparentPanel.setLayout(null); 
        backgroundPanel.add(semiTransparentPanel);

        JLabel lblWelcome = new JLabel("WELCOME TO", JLabel.CENTER);
        lblWelcome.setFont(customFont.deriveFont(Font.BOLD, 30)); 
        lblWelcome.setForeground(Color.BLACK); 
        lblWelcome.setOpaque(false);
        lblWelcome.setBounds(10, 10, 600, 50); 
        semiTransparentPanel.add(lblWelcome);

        JLabel lblTitle = new JLabel("AURUM MANILA", JLabel.CENTER);
        lblTitle.setFont(customFont.deriveFont(Font.BOLD, 50)); 
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setOpaque(false); 
        lblTitle.setBounds(10, 60, 600, 60); 
        semiTransparentPanel.add(lblTitle);

        JLabel lblRoomType = new JLabel("Choose your desired room type:", JLabel.CENTER);
        lblRoomType.setFont(customFont.deriveFont(Font.PLAIN, 20)); 
        lblRoomType.setForeground(Color.BLACK); 
        lblRoomType.setOpaque(false); 
        lblRoomType.setBounds(10, 180, 600, 30);
        semiTransparentPanel.add(lblRoomType);

        String[] roomTypes = {"Standard Room", "Twin Room", "Family Room", "Promo Room"};
        JComboBox<String> roomTypeComboBox = new JComboBox<>(roomTypes);

        roomTypeComboBox.setFont(customFont.deriveFont(Font.PLAIN, 18));
        roomTypeComboBox.setBounds(200, 230, 200, 50);

        roomTypeComboBox.setOpaque(false); 
        roomTypeComboBox.setBackground(new Color(0, 0, 0, 150)); 

        roomTypeComboBox.setRenderer(new javax.swing.plaf.basic.BasicComboBoxRenderer() {
            @Override
            public void paint(Graphics g) {
                setBackground(new Color(0, 0, 0, 150)); 
                setForeground(Color.WHITE); 
                super.paint(g);
            }
        });

        semiTransparentPanel.add(roomTypeComboBox); 

        JButton btnSubmit = createStyledButton("Go");
        btnSubmit.setBounds(150, 300, 100, 40);

        JButton btnCancel = createStyledButton("Back");
        btnCancel.setBounds(350, 300, 100, 40);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRoom = (String) roomTypeComboBox.getSelectedItem();
                if ("Standard Room".equals(selectedRoom)) {
                    new StandardRoomForm().setVisible(true);
                } else if ("Twin Room".equals(selectedRoom)) {
                    new TwinRoomForm().setVisible(true);
                } else if ("Family Room".equals(selectedRoom)) {
                    new FamilyRoomForm().setVisible(true);
                } else if ("Promo Room".equals(selectedRoom)) {
                    new PromoRoomForm().setVisible(true);
                }
                dispose(); 
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeForm().setVisible(true);
                dispose(); 
            }
        });

        semiTransparentPanel.add(btnSubmit);
        semiTransparentPanel.add(btnCancel);
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                URL imageUrl = getClass().getResource("/lobby.jpg"); 
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

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
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
        SwingUtilities.invokeLater(() -> new HomeForm().setVisible(true));
    }
}
