package Finals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Customer {
    private String name;
    private String contact;
    private long days; 
    private String email;
    private String payment;
    private String roomDetails;
    private double price;
    private String transactionNumber;
    private LocalDate checkInDate; 
    private LocalDate checkOutDate; 

    public Customer(String name, String contact, long days, String email, String payment, String roomDetails,
                    double price, LocalDate checkInDate, LocalDate checkOutDate, String transactionNumber) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (contact == null || contact.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact cannot be null or empty.");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be greater than zero.");
        }
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        this.name = name;
        this.contact = contact;
        this.days = days;
        this.email = email;
        this.payment = payment;
        this.roomDetails = roomDetails;
        this.price = price;
        this.transactionNumber = transactionNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkInDate.plusDays(days); 
    }

    // Getter methods
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public long getDays() {
        return days;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getPayment() {
        return payment;
    }

    public String getRoomDetails() {
        return roomDetails;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return Objects.equals(name, customer.name) &&
               Objects.equals(contact, customer.contact) &&
               Objects.equals(email, customer.email) &&
               Objects.equals(roomDetails, customer.roomDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contact, email, roomDetails, price, checkInDate);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Contact: %s, Days: %d, Payment: %s, Room: %s, Price: $%.2f, Check-in: %s, Transaction: %s",
                name, contact, days, payment, roomDetails, price, checkInDate, transactionNumber);
    }
}
