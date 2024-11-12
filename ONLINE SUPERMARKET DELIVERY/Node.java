package OnlineSupermarketDelivery;

// Represents a node in a doubly linked list used for storing item information.

public class Node {
   String item; // The name of the item
   double price; //price of the item
   Node next; // Reference to the next node in the list
   Node prev; // Reference to the previous node in the list 
  
   Node(String item, double price) {
       this.item = item; // Set the item name
       this.price = price; // Set the price name
       this.next = null; // Initialize the reference to the next node as null
       this.prev = null; // Initialize the reference to the previous node as null
   }
}



