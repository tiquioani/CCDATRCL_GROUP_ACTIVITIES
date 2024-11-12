package OnlineSupermarketDelivery;

public class Order {
    int orderId;
    String itemName;
    int quantity;
    String customerName;
    String phoneNumber;
    String address;
    Order next;

    public Order(int orderId, String itemName, int quantity, String customerName, String phoneNumber, String address) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.next = null;
    }
}
