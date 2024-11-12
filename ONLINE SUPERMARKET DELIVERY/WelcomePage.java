package OnlineSupermarketDelivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class WelcomePage extends JFrame {

    public WelcomePage() {
        setTitle("Welcome Page - COM232 ONLINE SUPERMARKET");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a custom panel with a gradient background
        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(new BorderLayout());
        
        // Create and configure the center panel with title and description
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Reduced vertical space
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("SHOPEASE");
        titleLabel.setForeground(Color.BLACK);
        Font titleFont = new Font("COURIER NEW", Font.BOLD, 60); 
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(titleLabel, gbc);

        // Description Label
        JLabel descriptionLabel = new JLabel("We make buying less difficult for you.");
        descriptionLabel.setForeground(Color.BLACK);
        descriptionLabel.setFont(new Font("COURIER NEW", Font.BOLD, 18)); 
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        gbc.gridy = 1;
        centerPanel.add(descriptionLabel, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20)); // Adjusted padding
        buttonPanel.setOpaque(false);

        JButton signInButton = createStyledButton("Log In", 150, 60);
        JButton signUpButton = createStyledButton("Sign Up", 150, 60);

        // Set font size for buttons
        signInButton.setFont(new Font("Courier New", Font.BOLD, 18));
        signUpButton.setFont(new Font("Courier New", Font.BOLD, 18));

        buttonPanel.add(signInButton);
        buttonPanel.add(signUpButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 30, 50)); // Adjusted bottom padding
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    // Create a styled button with custom appearance and behavior
    private JButton createStyledButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); 
        button.setBorderPainted(false); // Remove button border painting
        button.setContentAreaFilled(false); // Make the button's content area transparent

        button.setOpaque(true);
        button.setBackground(new Color(30, 30, 30, 200));

        // Add mouse listener for button hover effects
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 100, 100, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 30, 30, 200));
            }
        });
        
        // Add action listener to handle button clicks
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (text.equals("Log In")) {
                	// Handle "Log In" button click
                    Login loginFrame = new Login();
                    setVisible(false);
                    loginFrame.setVisible(true); // Display the login frame
                } else if (text.equals("Sign Up")) {
                    SignUp signupFrame = new SignUp();
                    setVisible(false);
                    signupFrame.setVisible(true);
                }
            }
        });

        return button;
    }
    // Custom panel class for displaying a background image
    class GradientPanel extends JPanel {
        private Image backgroundImage;

        public GradientPanel() {
            // Load the background image
            try {
                URL imageUrl = getClass().getResource("/forwelcomepic.jpg"); // Update this path
                backgroundImage = Toolkit.getDefaultToolkit().getImage(imageUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            // Draw the background image
            if (backgroundImage != null) {
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            g2d.dispose();
        }
    }

    public static void main(String[] args) {
    	// Start the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new WelcomePage(); // Create and display the WelcomePage
        });
    }
}


