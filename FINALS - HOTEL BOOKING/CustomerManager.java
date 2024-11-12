package Finals;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerManager {
    private static final String FILE_PATH = "customers.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    
    public static void addCustomer(Customer customer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String customerData = String.join(";",
                customer.getTransactionNumber(),
                customer.getName(),
                customer.getContact(),
                String.valueOf(customer.getDays()),
                customer.getEmail(),
                customer.getPayment(),
                customer.getRoomDetails(),
                String.valueOf(customer.getPrice()),
                customer.getCheckInDate().toString(),
                customer.getCheckOutDate().toString()
            );
            writer.write(customerData);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing customer data: " + e.getMessage());
        }
    }
    
    public static List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        Set<String> uniqueTransactionNumbers = new HashSet<>(); 
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                String[] data = line.split(";");
                if (data.length >= 10) {
                    String transactionNumber = data[0];
                    if (uniqueTransactionNumbers.contains(transactionNumber)) {
                        continue; 
                    }
                    uniqueTransactionNumbers.add(transactionNumber); 

                    String name = data[1];
                    String contact = data[2];
                    long days = Long.parseLong(data[3]);
                    String email = data[4];
                    String payment = data[5];
                    String roomDetails = data[6];
                    double price = Double.parseDouble(data[7]);
                    LocalDate checkInDate = LocalDate.parse(data[8]);
                    LocalDate checkOutDate = LocalDate.parse(data[9]);
                    customers.add(new Customer(name, contact, days, email, payment, roomDetails, price, checkOutDate, checkInDate, transactionNumber));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading customer data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in customer data: " + e.getMessage());
        }
        return customers;
    }
    
    public static List<Customer> filterCustomersByDate(List<Customer> customers, LocalDate startDate, LocalDate endDate) {
        List<Customer> filteredCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            LocalDate checkIn = customer.getCheckInDate();
            LocalDate checkOut = customer.getCheckOutDate();
            
            if ((checkIn.isBefore(endDate) && checkOut.isAfter(startDate))) {
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }
}
