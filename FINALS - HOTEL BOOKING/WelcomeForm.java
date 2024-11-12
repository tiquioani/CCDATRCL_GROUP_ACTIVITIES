package Finals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WelcomeForm extends JFrame {
    private Font customFont;

    public WelcomeForm() {
        loadCustomFont();
        setTitle("WELCOME - Aurum Manila");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnYes = createStyledButton("Book Now", 170, 70);
        JButton btnNo = createStyledButton("Another Time", 170, 70);
        JButton btnViewQueue = createStyledButton("View Bookings", 170, 70);

        btnYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HomeForm().setVisible(true); 
                dispose();
            }
        });

        btnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnViewQueue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueueDisplayForm().setVisible(true);
            }
        });

        JLabel lblTitle = new JLabel("AURUM MANILA", JLabel.CENTER);
        lblTitle.setFont(customFont.deriveFont(Font.BOLD, 50));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(0, 0, 0, 150)); 
        lblTitle.setBounds(100, 100, 470, 50); 

        JLabel lblSubtitle = new JLabel("Discover your perfect stay! Explore our exclusive rooms and book now.", JLabel.CENTER);
        lblSubtitle.setFont(customFont.deriveFont(Font.PLAIN, 14));
        lblSubtitle.setForeground(Color.WHITE);
        lblSubtitle.setOpaque(true);
        lblSubtitle.setBackground(new Color(0, 0, 0, 150)); 
        lblSubtitle.setBounds(100, 150, 470, 60); 
        lblSubtitle.setHorizontalAlignment(JLabel.CENTER); 

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(null); 
        labelPanel.setOpaque(false); 
        labelPanel.setPreferredSize(new Dimension(700, 200));

        labelPanel.add(lblTitle);
        labelPanel.add(lblSubtitle);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null); 
        buttonPanel.setOpaque(false); 
        buttonPanel.setPreferredSize(new Dimension(700, 100)); 

        btnYes.setBounds(70, 20, 150, 50); 
        btnNo.setBounds(270, 20, 150, 50); 
        btnViewQueue.setBounds(470, 20, 150, 50); 

        buttonPanel.add(btnYes);
        buttonPanel.add(btnNo);
        buttonPanel.add(btnViewQueue);

        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(labelPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    class GradientPanel extends JPanel {
        private Image backgroundImage;

        public GradientPanel() {
            try {
                URL imageUrl = getClass().getResource("/wcpic.jpeg"); 
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

    // Method to create a styled button
    private JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setForeground(Color.WHITE); 
        button.setFocusPainted(false);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 

        button.setOpaque(true);
        button.setBackground(new Color(0, 0, 0, 150)); 

        button.setFont(customFont.deriveFont(Font.BOLD, 15)); 

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 0, 100)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 0, 150)); 
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
        SwingUtilities.invokeLater(() -> new WelcomeForm().setVisible(true));
    }
}

