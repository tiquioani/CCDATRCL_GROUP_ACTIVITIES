package OnlineSupermarketDelivery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineSupermarketDelivery extends JFrame {
    private static final long serialVersionUID = 1L;

    // SinglyLinkedList to store orders
    private SinglyLinkedList orders = new SinglyLinkedList();

    // Text fields and text area for form input
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextArea itemsTextArea;

    public OnlineSupermarketDelivery() {
        setTitle("ACTIVITY #3 in Data Structures and Algorithm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create the main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(247, 220, 111));
        getContentPane().add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the title label
        JLabel titleLabel = new JLabel("COM232 Supermarket Delivery Form", JLabel.CENTER);
        titleLabel.setFont(new Font("Courier New", Font.BOLD, 26));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        // Create the form panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(252, 243, 207));

        GridBagConstraints gbcForm = new GridBagConstraints();
        gbcForm.insets = new Insets(5, 5, 5, 5);
        gbcForm.fill = GridBagConstraints.HORIZONTAL;

        // Labels and text fields
        String[] labels = {"Customer Name:", "Address:", "Phone Number:", "Items to purchase (comma separated):"};

        int y = 0;
        for (String label : labels) {
            gbcForm.gridx = 0;
            gbcForm.gridy = y;
            gbcForm.anchor = GridBagConstraints.EAST;
            formPanel.add(new JLabel(label), gbcForm);

            gbcForm.gridx = 1;
            gbcForm.gridy = y;
            gbcForm.anchor = GridBagConstraints.WEST;

            if (label.equals("Items to purchase (comma separated):")) {
                itemsTextArea = new JTextArea(5, 20);
                itemsTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
                formPanel.add(new JScrollPane(itemsTextArea), gbcForm);
            } else {
                JTextField textField = new JTextField(20);
                if (label.equals("Customer Name:")) {
                    nameField = textField;
                } else if (label.equals("Address:")) {
                    addressField = textField;
                } else if (label.equals("Phone Number:")) {
                    phoneField = textField;
                }
                formPanel.add(textField, gbcForm);
            }
            y++;
        }

        // Submit button
        gbcForm.gridx = 1;
        gbcForm.gridy = y;
        gbcForm.anchor = GridBagConstraints.CENTER;
        JButton submitButton = new JButton("Submit Order");
        submitButton.setFont(new Font("Courier New", Font.BOLD, 16));
        formPanel.add(submitButton, gbcForm);

        // Add form panel to main panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(formPanel, gbc);

        // Button action listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneField.getText();
                String items = itemsTextArea.getText();

                if (name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || items.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill out all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create an order and add it to the singly linked list
                Order order = new Order(name, address, phoneNumber, items);
                orders.add(order);

                // Show confirmation
                JOptionPane.showMessageDialog(null,
                        "Your order is submitted!\n" +
                                "Name: " + name + "\n" +
                                "Address: " + address + "\n" +
                                "Phone Number: " + phoneNumber + "\n" +
                                "Items: " + items,
                        "ORDER CONFIRMATION",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear fields after submission
                nameField.setText("");
                addressField.setText("");
                phoneField.setText("");
                itemsTextArea.setText("");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OnlineSupermarketDelivery());
    }

    // Inner class to represent an Order
    private class Order {
        private String name;
        private String address;
        private String phoneNumber;
        private String items;

        public Order(String name, String address, String phoneNumber, String items) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.items = items;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", items='" + items + '\'' +
                    '}';
        }
    }

    // Inner class for SinglyLinkedList
    private class SinglyLinkedList {
        private Node head;

        private class Node {
            Order order;
            Node next;

            Node(Order order) {
                this.order = order;
                this.next = null;
            }
        }

        // Add a new order to the linked list
        public void add(Order order) {
            Node newNode = new Node(order);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        // Display all orders in the linked list
        public void display() {
            Node current = head;
            while (current != null) {
                System.out.println(current.order);
                current = current.next;
            }
        }
    }
}
