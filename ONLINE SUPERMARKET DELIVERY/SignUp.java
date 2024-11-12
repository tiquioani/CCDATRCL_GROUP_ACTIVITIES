package OnlineSupermarketDelivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField nameField;
    private JTextField emailField;

    public SignUp() {
        super("Sign Up - COM232 ONLINE SUPERMARKET");
        
        // Create a custom panel with a gradient background
        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(new BorderLayout());

        // Create a form panel with GridBagLayout for user input fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username: "), gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Password: "), gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Confirm Password: "), gbc);

        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        formPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Name: "), gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Email: "), gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        // Create and configure the sign up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Courier New", Font.BOLD, 14));
        signUpButton.addActionListener(new ActionListener() {
            @Override
             // Retrieve user input
             public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String confirmPassword = new String(confirmPasswordField.getPassword()).trim();
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();

                // Validate input fields
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || name.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                } else if (password.length()<6) {
                    JOptionPane.showMessageDialog(null, "Passwords must be 6 or more characters.");
                } else {
                	// Add the new user and show success message
                    UserManager.addUser(username, password);
                    JOptionPane.showMessageDialog(null, "User created successfully!");
                    // Open the welcome page and hide the sign-up window
                    WelcomePage frame = new WelcomePage();
                    setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        buttonPanel.add(signUpButton);
        
        // Create and configure the back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Courier New", Font.BOLD, 14));
        backButton.addActionListener(new ActionListener() {
            @Override
            // Go back to the welcome page and hide the sign-up window
            public void actionPerformed(ActionEvent e) {
                WelcomePage frame = new WelcomePage();
                frame.setVisible(true);
                setVisible(false);
            }
        });
        buttonPanel.add(backButton);

        formPanel.add(buttonPanel, gbc);

        contentPane.add(formPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        SwingUtilities.invokeLater(() -> {
            new SignUp().setVisible(true);
        });
    }
}
