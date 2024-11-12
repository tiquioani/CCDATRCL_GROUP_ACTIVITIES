package OnlineSupermarketDelivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeliveryForm extends JFrame {
    private JTextField nameField, addressField, contactField, noteField;
    private JButton submitButton, backButton;
    private DoublyLinkedList cart;
    private GroceryListGUI groceryListGUI;
    private JLabel totalAmountLabel;
    private JTextArea itemsDetailArea; // Text area to display items and prices
    private JLabel itemsDetailTitle; // Title for the items and prices section

    // Constructor to initialize the DeliveryForm with the given cart and grocery list GUI
    public DeliveryForm(DoublyLinkedList cart, GroceryListGUI groceryListGUI) {
        this.cart = cart;
        this.groceryListGUI = groceryListGUI;
        
        initializeComponents();
        initializeUI();
    }

    private void initializeComponents() {
        nameField = new JTextField(40);
        addressField = new JTextField(40);
        contactField = new JTextField(40);
        noteField = new JTextField(40);
        
        submitButton = createStyledButton("Submit");
        backButton = createStyledButton("Back");
        
        Dimension buttonSize = new Dimension(120, 30); // Width: 120, Height: 30
        submitButton.setPreferredSize(buttonSize);
        backButton.setPreferredSize(buttonSize);
        
        totalAmountLabel = new JLabel();
        
        itemsDetailArea = new JTextArea(10, 40); // Adjust size as needed
        itemsDetailArea.setEditable(false);
        itemsDetailArea.setFont(new Font("Arial", Font.PLAIN, 14));
        itemsDetailArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        itemsDetailTitle = new JLabel("Items and Prices:");
        itemsDetailTitle.setFont(new Font("Arial", Font.BOLD, 12));
        itemsDetailTitle.setForeground(Color.BLACK);
        
        submitButton.addActionListener(e -> handleSubmit());
        backButton.addActionListener(e -> handleBackButton());
    }
    // Initializes the user interface, including layout, components, and event handling
    private void initializeUI() {
        GradientPanel contentPane = new GradientPanel();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        contentPane.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        contentPane.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(new JLabel("Contact Number:"), gbc);
        gbc.gridx = 1;
        contentPane.add(contactField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(new JLabel("Note (optional only):"), gbc);
        gbc.gridx = 1;
        contentPane.add(noteField, gbc);

        // Add items detail title
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(itemsDetailTitle, gbc);

        // Add items details text area
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPane.add(new JScrollPane(itemsDetailArea), gbc);

        // Add total amount label
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.0;
        totalAmountLabel.setText("Total Amount: ₱" + String.format("%.2f", calculateTotalAmount()));
        contentPane.add(totalAmountLabel, gbc);

        // Add buttons
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(submitButton, gbc);

        gbc.gridx = 1;
        contentPane.add(backButton, gbc);

        setContentPane(contentPane);
        
        setTitle("Delivery Form - COM232 ONLINE SUPERMARKET");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Courier New", Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        return button;
    }
    // Calculates the total amount of items in the cart and updates the items detail area
    private double calculateTotalAmount() {
        double totalAmount = 0.0;
        StringBuilder details = new StringBuilder(); // StringBuilder to hold item details

        for (String item : cart.getItems()) {
            double price = groceryListGUI.getPriceMap().getOrDefault(item, 0.0);
            totalAmount += price;
            details.append(String.format("%s - ₱%.2f\n", item, price));
        }

        itemsDetailArea.setText(details.toString());
        return totalAmount;
    }
    // Handles the submit action for the delivery form
    private void handleSubmit() {
        String name = nameField.getText();
        String address = addressField.getText();
        String contact = contactField.getText();
        String notes = noteField.getText();
        
        if (name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.");
        } else {
            JOptionPane.showMessageDialog(this, "Delivery Form Submitted!\n" +
                    "Name: " + name + "\n" +
                    "Address: " + address + "\n" +
                    "Contact: " + contact + "\n" +
                    "Notes: " + notes);
            
            this.setVisible(false);
            groceryListGUI.setVisible(true);
        }
    }
    // Handles the back button action to return to the grocery list GUI
    private void handleBackButton() {
        this.setVisible(false);
        groceryListGUI.setVisible(true);
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            
            // Define the colors for the gradient
            Color color1 = new Color(230, 190, 255); // Light purple color
            Color color2 = new Color(200, 160, 230); // Slightly darker light purple color
            
            // Create a gradient paint from top to bottom
            GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            
            g2d.dispose();
        }
    }
    // Main method to launch the DeliveryForm application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DeliveryForm(new DoublyLinkedList(), new GroceryListGUI()).setVisible(true));
    }
}




