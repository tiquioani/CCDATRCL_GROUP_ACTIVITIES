package OnlineSupermarketDelivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class GroceryListGUI extends JFrame {
    private DoublyLinkedList groceryList;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JButton buySelectedButton, proceedToCheckoutButton, viewCartButton, backButton, searchButton;
    private JTextField searchField;
    private DoublyLinkedList cart;
    private static HashMap<String, Double> priceMap;

    // Constructor that initializes the grocery list and UI.
    public GroceryListGUI() {
        groceryList = new DoublyLinkedList();
        cart = new DoublyLinkedList(); // Initialize cart
        priceMap = new HashMap<>();
        initializeGroceryList(); // Populate the grocery list and price map
        initializeUI(); // Setup the user interface
    }
    
    private void initializeGroceryList() {
    	  groceryList.add("***************GRAINS***************");
          groceryList.add("Oats");
          priceMap.put("Oats", 60.00);
          groceryList.add("Rice");
          priceMap.put("Rice", 70.00);
          groceryList.add("Corn");
          priceMap.put("Corn", 100.00);
          groceryList.add("Quinoa");
          priceMap.put("Quinoa", 110.00);
          groceryList.add("Bulgur");
          priceMap.put("Bulgur", 110.00);
          groceryList.add("***************DAIRY***************");
          groceryList.add("Yogurt");
          priceMap.put("Yogurt", 120.00);
          groceryList.add("Fresh Milk");
          priceMap.put("Fresh Milk", 140.00);
          groceryList.add("Almond Milk");
          priceMap.put("Almond Milk", 180.00);
          groceryList.add("Butter");
          priceMap.put("Butter", 60.00);
          groceryList.add("Cheese");
          priceMap.put("Cheese", 70.00);
          groceryList.add("Cream");
          priceMap.put("Cream", 90.00);
          groceryList.add("Eggs");
          priceMap.put("Eggs", 120.00);
          groceryList.add("***************FRUITS***************");
          groceryList.add("Apples");
          priceMap.put("Apples", 90.00);
          groceryList.add("Bananas");
          priceMap.put("Bananas", 80.00);
          groceryList.add("Strawberries");
          priceMap.put("Strawberries", 190.00);
          groceryList.add("Avocados");
          priceMap.put("Avocados", 200.00);
          groceryList.add("Oranges");
          priceMap.put("Oranges", 120.00);
          groceryList.add("Grapes");
          priceMap.put("Grapes", 150.00);
          groceryList.add("Mangoes");
          priceMap.put("Mangoes", 160.00);
          groceryList.add("***************VEGETABLES***************");
          groceryList.add("Onion");
          priceMap.put("Onion", 70.00);
          groceryList.add("Garlic");
          priceMap.put("Garlic", 70.00);
          groceryList.add("Ginger");
          priceMap.put("Ginger", 50.00);
          groceryList.add("Tomato");
          priceMap.put("Tomato", 60.00);
          groceryList.add("Squash");
          priceMap.put("Squash", 80.00);
          groceryList.add("Potatoes");
          priceMap.put("Potatoes", 100.00);
          groceryList.add("Carrots");
          priceMap.put("Carrots", 80.00);
          groceryList.add("Green Peas");
          priceMap.put("Green Peas", 90.00);
          groceryList.add("Eggplants");
          priceMap.put("Eggplants", 70.00);
          groceryList.add("Broccoli");
          priceMap.put("Broccoli", 120.00);
          groceryList.add("Lettuce");
          priceMap.put("Lettuce", 100.00);
          groceryList.add("***************MEATS***************");
          groceryList.add("Chicken");
          priceMap.put("Chicken", 150.00);
          groceryList.add("Beef");
          priceMap.put("Beef", 200.00);
          groceryList.add("Pork");
          priceMap.put("Pork", 180.00);
          groceryList.add("***************SEAFOODS***************");
          groceryList.add("Fish");
          priceMap.put("Fish", 100.00);
          groceryList.add("Prawns");
          priceMap.put("Prawns", 120.00);
          groceryList.add("Crabs");
          priceMap.put("Crabs", 350.00);
          groceryList.add("Mussels");
          priceMap.put("Mussels", 150.00);
          groceryList.add("Salmon");
          priceMap.put("Salmon", 200.00);
          groceryList.add("***************CANNED GOODS***************");
          groceryList.add("Tomato Sauce");
          priceMap.put("Tomato Sauce", 70.00);
          groceryList.add("Tuna");
          priceMap.put("Tuna", 50.00);
          groceryList.add("Corned Beef");
          priceMap.put("Corned Beef", 100.00);
          groceryList.add("Sardines");
          priceMap.put("Sardines", 40.00);
          groceryList.add("Coconut Milk");
          priceMap.put("Coconut Milk", 90.00);
          groceryList.add("***************INSTANT FOODS***************");
          groceryList.add("Instant Noodles");
          priceMap.put("Instant Noodles", 15.00);
          groceryList.add("Coffee");
          priceMap.put("Coffee", 20.00);
          groceryList.add("Pasta");
          priceMap.put("Pasta", 80.00);
          groceryList.add("***************CONDIMENTS***************");
          groceryList.add("Soy Sauce");
          priceMap.put("Soy Sauce", 50.00);
          groceryList.add("Vinegar");
          priceMap.put("Vinegar", 50.00);
          groceryList.add("Olive Oil");
          priceMap.put("Olive Oil", 150.00);
          groceryList.add("Ketchup");
          priceMap.put("Ketchup", 90.00);
          groceryList.add("***************BEVERAGES***************");
          groceryList.add("Distilled Water (500 mL)");
          priceMap.put("Distilled Water (500 mL)", 35.00);
          groceryList.add("Apple Juice (500 mL)");
          priceMap.put("Apple Juice (500 mL)", 80.00);
          groceryList.add("Orange Juice");
          priceMap.put("Orange Juice", 80.00);
          groceryList.add("Cranberry Juice (500 mL)");
          priceMap.put("Cranberry Juice (500 mL)", 90.00);
          groceryList.add("Soda (500 mL)");
          priceMap.put("Soda (500 mL)", 45.00);
          groceryList.add("***************PASTRIES***************");
          groceryList.add("Loaf Bread");
          priceMap.put("Loaf Bread", 60.00);
          groceryList.add("Donuts");
          priceMap.put("Donuts", 120.00);
          groceryList.add("Cookies");
          priceMap.put("Cookies", 120.00);
          groceryList.add("Pretzels");
          priceMap.put("Pretzels", 80.00);
          groceryList.add("***************SNACKS***************");
          groceryList.add("Plain Biscuits");
          priceMap.put("Plain Biscuits", 30.00);
          groceryList.add("Chocolates");
          priceMap.put("Chocolates", 80.00);
          groceryList.add("Fruit Candies");
          priceMap.put("Fruit Candies", 50.00);
          groceryList.add("Popcorn");
          priceMap.put("Popcorn", 50.00);
          groceryList.add("***************HOUSEHOLD ITEMS***************");
          groceryList.add("Dishwashing Liquid");
          priceMap.put("Dishwashing Liquid", 120.00);
          groceryList.add("Detergent Soap");
          priceMap.put("Detergent Soap", 250.00);
          groceryList.add("Fabric Conditioner");
          priceMap.put("Fabric Conditioner", 250.00);
          groceryList.add("Bath Soap");
          priceMap.put("Bath Soap", 110.00);
          groceryList.add("Shampoo");
          priceMap.put("Shampoo", 130.00);
          groceryList.add("Toothpaste");
          priceMap.put("Toothpaste", 100.00);
          groceryList.add("Toilet Paper");
          priceMap.put("Toilet Paper", 25.00);
    }

    // Initializes the user interface including the grocery list, buttons, and layout
    private void initializeUI() {
        listModel = new DefaultListModel<>();
        for (String item : groceryList.getItems()) {
            listModel.addElement(item);
        }
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setBackground(Color.decode("#d7bde2"));
        list.setFont(new Font("Arial", Font.PLAIN, 14));
        list.setCellRenderer(new TwoColumnListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(list);

        // Initialize buttons
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        buySelectedButton = new JButton("Select Item");
        proceedToCheckoutButton = new JButton("Proceed to Delivery Form");
        viewCartButton = new JButton("View Your Cart");
        backButton = new JButton("Sign out");

        setUpButtons();
        setUpPanel(scrollPane);

        setTitle("Grocery List - COM232 ONLINE SUPERMARKET");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    //  Setup the actions and styles for the buttons
    private void setUpButtons() {
        buySelectedButton.setFont(new Font("Courier New", Font.BOLD, 14));
        proceedToCheckoutButton.setFont(new Font("Courier New", Font.BOLD, 14));
        viewCartButton.setFont(new Font("Courier New", Font.BOLD, 14));
        backButton.setFont(new Font("Courier New", Font.BOLD, 14));
        searchButton.setFont(new Font("Courier New", Font.BOLD, 14));;

        searchButton.addActionListener(e -> performSearch());
        buySelectedButton.addActionListener(e -> handleBuySelected());
        viewCartButton.addActionListener(e -> showCartGUI());
        backButton.addActionListener(e -> handleBackButton());
        proceedToCheckoutButton.addActionListener(e -> showDeliveryForm());

        // Set button backgrounds and foregrounds
        setButtonStyles();
    }

    private void setButtonStyles() {
        buySelectedButton.setBackground(Color.WHITE);
        proceedToCheckoutButton.setBackground(Color.WHITE);
        viewCartButton.setBackground(Color.WHITE);
        backButton.setBackground(Color.WHITE);
        searchButton.setBackground(Color.WHITE);
        buySelectedButton.setForeground(Color.BLACK);
        proceedToCheckoutButton.setForeground(Color.BLACK);
        viewCartButton.setForeground(Color.BLACK);
        backButton.setForeground(Color.BLACK);
        searchButton.setForeground(Color.BLACK);
        
    }
    // Sets up the panel with the scroll pane and buttons
    // the parameter scrollPane = scroll pane containing the grocery list
    
    private void setUpPanel(JScrollPane scrollPane) {
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search Item:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.setBackground(new Color(255, 255, 255, 128)); // Semi-transparent white

        backgroundPanel.add(searchPanel, BorderLayout.NORTH);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buySelectedButton);
        buttonPanel.add(viewCartButton);
        buttonPanel.add(proceedToCheckoutButton);
        buttonPanel.add(backButton);
        buttonPanel.setBackground(new Color(255, 255, 255, 128)); // Semi-transparent white
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(backgroundPanel);
    }
    // Handles the action of searching for an item in the cart
    private void performSearch() {
    	// Retrieve the search query from the searchField and 
    	// convert it to lower case for case-insensitive comparison
        String query = searchField.getText().toLowerCase();
        // Clear the existing items in the listModel to prepare for the search results
        listModel.clear();
        for (String item : groceryList.getItems()) {
        	// Check if the current item contains the search query
            if (item.toLowerCase().contains(query)) {
            	// If the item matches the search query, add it to the listModel
                listModel.addElement(item);
            }
        }
    }
    // Handles the action of adding selected items to the cart
    private void handleBuySelected() {
        List<String> selectedItems = list.getSelectedValuesList();
        if (!selectedItems.isEmpty()) {
            for (String item : selectedItems) {
                if (priceMap.containsKey(item)) {
                    cart.add(item);
                }
            }
            JOptionPane.showMessageDialog(null,
                    selectedItems.size() + " item(s) added to your cart.",
                    "Items Added",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No item(s) selected.");
        }
    }
    // Displays the cart GUI
    private void showCartGUI() {
        new CartGUI(cart, this).setVisible(true);
        setVisible(false);
    }
    // Handles the action of signing out and showing the welcome page
    private void handleBackButton() {
        new WelcomePage().setVisible(true);
        setVisible(false);
    }
    // Displays the delivery form
    private void showDeliveryForm() {
        DeliveryForm deliveryForm = new DeliveryForm(cart, this);
        deliveryForm.setVisible(true);
        this.setVisible(false);
    }
    // Returns the price map for items
    public HashMap<String, Double> getPriceMap() {
        return priceMap;
    }
    // Custom cell renderer to display items in two columns with their prices
    private static class TwoColumnListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                String item = (String) value;
                if (priceMap.containsKey(item)) {
                    label.setText(String.format("<html><div style='text-align: left;'>%s - ₱%.2f</div></html>", item, priceMap.get(item)));
                } else {
                    label.setText(String.format("<html><div style='text-align: left;'>%s</div></html>", item));
                }
            }
            return component;
        }
    }

    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = Toolkit.getDefaultToolkit().createImage("path/to/your/image.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GroceryListGUI().setVisible(true));
    }
}
