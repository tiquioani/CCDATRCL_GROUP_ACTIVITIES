package OnlineSupermarketDelivery;

import java.util.ArrayList;
import java.util.List;


 // Represents a doubly linked list that supports basic operations such as adding,
 // removing, and retrieving items.
 
public class DoublyLinkedList {
    private Node head; // Reference to the first node in the list
    private Node tail; // Reference to the last node in the list

    
     // Constructs an empty doubly linked list
    public DoublyLinkedList() {
        head = null; // Initialize head as null
        tail = null; // Initialize tail as null
    }

    
    // Adds a new item to the end of the list
    public void add(String item) {
        Node newNode = new Node(item); // Create a new node with the specified item
        if (head == null) {
            // List is empty, so the new node becomes both head and tail
            head = newNode;
            tail = newNode;
        } else {
            // List is not empty, append the new node to the end
            tail.next = newNode; // Update the current tail's next reference
            newNode.prev = tail; // Set the new node's previous reference
            tail = newNode; // Update tail to be the new node
        }
    }

    
    // Removes the first occurrence of the specified item from the list
    public void remove(String item) {
        Node current = head; // Start from the head of the list
        
        while (current != null) {
            if (current.data.equals(item)) {
                // Item found, remove the node
                if (current.prev != null) {
                    current.prev.next = current.next; // Update the previous node's next reference
                } else {
                    head = current.next; // Update head if the first node is removed
                }
                
                if (current.next != null) {
                    current.next.prev = current.prev; // Update the next node's previous reference
                } else {
                    tail = current.prev; // Update tail if the last node is removed
                }
                
                // Handle the case where the list becomes empty
                if (head == null) {
                    tail = null; // If the list is empty, set tail to null
                }
                
                break; // Exit the loop once the node is removed
            }
            current = current.next; // Move to the next node
        }
    }

    
     // Retrieves all items in the list as an array
    public String[] getItems() {
        List<String> items = new ArrayList<>(); // List to store items temporarily
        Node current = head; // Start from the head of the list
        while (current != null) {
            items.add(current.data); // Add current node's data to the list
            current = current.next; // Move to the next node
        }
        return items.toArray(new String[0]); // Convert the list to an array and return
    }
    
     // Returns the head node of the list
    public Node getHead() {
        return head; // Return the reference to the head node
    }

    // Clears the list by removing all nodes 
    public void clear() {
        head = null; // Set head to null
        tail = null; // Set tail to null
    }
    
    // Represents a node in the doubly linked list
    static class Node {
        String data; // The data stored in the node
        Node prev; // Reference to the previous node
        Node next; // Reference to the next node

         // Constructs a new node with the specified data  
        public Node(String data) {
            this.data = data; // Set the node's data
            prev = null; // Initialize previous reference as null
            next = null; // Initialize next reference as null
        }
    }
}

