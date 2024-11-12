package OnlineSupermarketDelivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    public Login() {
        super("Login - COM232 ONLINE SUPERMARKET");

        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(new BorderLayout());
        
        // Create a panel for form components
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);
        
        // Create and position the username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(150, 80, 100, 25);
        panel.add(usernameLabel);
        
        // Create and position the username text field
        JTextField usernameField = new JTextField(20);
        usernameField.setBounds(260, 80, 200, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(150, 120, 100, 25);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(260, 120, 200, 25);
        panel.add(passwordField);
        
        // Create and configure the login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Courier New", Font.BOLD, 14));
        loginButton.setBounds(260, 170, 100, 30);
        loginButton.setPreferredSize(new Dimension(150, 30)); // Adjusted width
        loginButton.addActionListener(new ActionListener() {
            @Override
            // Retrieve user input
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                
                // Validate user credentials
                if (UserManager.isValidUser(username, password)) {
                	// Open the grocery list GUI and hide the login window
                    new GroceryListGUI().setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!");
                }
            }
        });
 
        // Create and configure the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Courier New", Font.BOLD, 14));
        backButton.setBounds(370, 170, 100, 30);
        backButton.setPreferredSize(new Dimension(150, 30)); // Adjusted width
        backButton.addActionListener(new ActionListener() {
            @Override
            // Return to the welcome page and hide the login window
            public void actionPerformed(ActionEvent e) {
                new WelcomePage().setVisible(true);
                setVisible(false);
            }
        });

        panel.add(loginButton);
        panel.add(backButton);

        contentPane.add(panel, BorderLayout.CENTER);

        setContentPane(contentPane);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    // Inner class to create a panel with a gradient background
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();

            int width = getWidth();
            int height = getHeight();

            Color color1 = new Color(230, 190, 255);
            Color color2 = new Color(200, 160, 230);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);

            drawStars(g2d, width, height);

            g2d.dispose();
        }

        private void drawStars(Graphics2D g2d, int width, int height) {
            g2d.setColor(new Color(245, 215, 245));
            for (int i = 0; i < 100; i++) {
                int x = (int) (Math.random() * width);
                int y = (int) (Math.random() * height);
                int size = (int) (Math.random() * 2) + 1;
                g2d.fillRect(x, y, size, size);
            }
        }
    }
    // Main method to start the application
    public static void main(String[] args) {
        // Initialize the user list
        UserManager.addUser("testuser", "testpassword");

        SwingUtilities.invokeLater(() -> new Login());
    }
}
