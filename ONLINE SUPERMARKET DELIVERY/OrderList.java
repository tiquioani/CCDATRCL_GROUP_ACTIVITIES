package OnlineSupermarketDelivery;

public class OrderList {
    private Order head;

    public OrderList() {
        this.head = null;
    }

    public void addOrder(int orderId, String itemName, int quantity, String customerName, String phoneNumber, String address) {
        Order newOrder = new Order(orderId, itemName, quantity, customerName, phoneNumber, address);
        if (head == null) {
            head = newOrder;
        } else {
            Order current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newOrder;
        }
    }

    public void removeOrder(int orderId) {
        if (head == null) return;

        if (head.orderId == orderId) {
            head = head.next;
            return;
        }

        Order current = head;
        while (current.next != null && current.next.orderId != orderId) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public String viewOrders() {
        StringBuilder sb = new StringBuilder();
        Order current = head;
        while (current != null) {
            sb.append("Order ID: ").append(current.orderId)
              .append(", Item: ").append(current.itemName)
              .append(", Quantity: ").append(current.quantity)
              .append(", Customer Name: ").append(current.customerName)
              .append(", Phone: ").append(current.phoneNumber)
              .append(", Address: ").append(current.address)
              .append("\n");
            current = current.next;
        }
        return sb.toString();
    }
}
