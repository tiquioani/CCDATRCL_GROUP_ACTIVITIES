package OnlineSupermarketDelivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CartGUI extends JFrame {
    private DoublyLinkedList cartList; // List to store cart items
    private JList<String> cartDisplayList; // Component to display cart items
    private DefaultListModel<String> cartListModel; 
    private JButton removeItemButton;
    private JButton clearCartButton;
    private JButton backCartButton;
    private JLabel totalPriceLabel; // Label to display total price
    private GroceryListGUI groceryListGUI; // Reference to the original GroceryListGUI
    private HashMap<String, Double> priceMap; // Store prices
  
    
    public CartGUI(DoublyLinkedList cartList, GroceryListGUI groceryListGUI) {
        this.cartList = cartList;
        this.groceryListGUI = groceryListGUI;
        this.priceMap = groceryListGUI.getPriceMap(); // Access the price map from GroceryListGUI

        // Initialize GUI components
        cartListModel = new DefaultListModel<>();
        cartDisplayList = new JList<>(cartListModel);
        cartDisplayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cartDisplayList.setBackground(Color.decode("#d7bde2"));
        cartDisplayList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(cartDisplayList);

        removeItemButton = new JButton("Remove Selected Item");
        clearCartButton = new JButton("Clear Cart");
        backCartButton = new JButton("Go back");

        totalPriceLabel = new JLabel("Total: ₱0.00");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Set fonts and colors for buttons
        removeItemButton.setFont(new Font("Courier New", Font.BOLD, 14));
        clearCartButton.setFont(new Font("Courier New", Font.BOLD, 14));
        backCartButton.setFont(new Font("Courier New", Font.BOLD, 14));

        removeItemButton.setBackground(Color.WHITE);
        clearCartButton.setBackground(Color.WHITE);
        backCartButton.setBackground(Color.WHITE);

        removeItemButton.setForeground(Color.BLACK);
        clearCartButton.setForeground(Color.BLACK);
        backCartButton.setForeground(Color.BLACK);

        // Set up event listeners
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            // Remove the selected item from the cart
            public void actionPerformed(ActionEvent e) {
                String selectedItem = cartDisplayList.getSelectedValue();
                if (selectedItem != null) {
                    cartList.remove(selectedItem);
                    updateCartDisplay(); // Update the display after removal
                } else {
                    JOptionPane.showMessageDialog(null, "No item selected to remove.");
                }
            }
        });

        clearCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartList.clear(); // Clear all items from the cart
                updateCartDisplay();
            }
        });

        backCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groceryListGUI.setVisible(true); // Show the existing GroceryListGUI
                setVisible(false); // Hide the current CartGUI
            }
        });

        // Layout setup
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeItemButton);
        buttonPanel.add(clearCartButton);
        buttonPanel.add(backCartButton);
        buttonPanel.setBackground(Color.WHITE);
        add(buttonPanel, BorderLayout.SOUTH);

        JPanel totalPanel = new JPanel();
        totalPanel.add(totalPriceLabel);
        totalPanel.setBackground(Color.WHITE);
        add(totalPanel, BorderLayout.NORTH);

        setTitle("Customer's Cart - COM232 ONLINE SUPERMARKET");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set the main frame background color
        getContentPane().setBackground(Color.WHITE);

        // Initially update the cart display
        updateCartDisplay();
    }

    // Method to update cart display list based on current items in cartList
    private void updateCartDisplay() {
        if (totalPriceLabel == null) {
            System.err.println("Error: totalPriceLabel is not initialized.");
            return;
        }

        cartListModel.clear();
        double total = 0.0;

        for (String item : cartList.getItems()) {
            cartListModel.addElement(item);
            if (priceMap.containsKey(item)) {
                total += priceMap.get(item);
            }
        }

        totalPriceLabel.setText(String.format("Total: ₱%.2f", total));
    }

    // Main method for testing CartGUI independently
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DoublyLinkedList cart = new DoublyLinkedList();
            new CartGUI(cart, new GroceryListGUI()).setVisible(true); // Pass new GroceryListGUI for testing
        });
    }
}
