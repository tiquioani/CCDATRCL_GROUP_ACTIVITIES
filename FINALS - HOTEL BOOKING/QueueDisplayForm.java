package Finals;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class QueueDisplayForm extends JFrame {
    private JTable tblQueue;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> roomTypeComboBox;
    private JComboBox<String> dateComboBox;

    public QueueDisplayForm() {
        setTitle("CUSTOMER BOOKING - Aurum Manila");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeUI();
        loadCustomerData();
    }

    private void initializeUI() {
        String[] columnNames = {"Transaction No.", "Full Name", "Days of Stay", "Room Type", "CheckOut Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblQueue = new JTable(tableModel);
        tblQueue.setFont(new Font("Arial", Font.PLAIN, 14));
        tblQueue.setRowHeight(30);
        tblQueue.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblQueue.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tblQueue);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.setOpaque(false);

        searchField = new JTextField(15);
        searchField.setToolTipText("Search by Full Name");
        searchField.addActionListener(e -> updateQueueDisplay());

        String[] roomTypes = {"All Rooms", "ST101", "ST102", "ST103", "TW101", "TW102", "TW103",
                              "FR101", "FR102", "FR103", "PR101", "PR102", "PR103"};
        roomTypeComboBox = new JComboBox<>(roomTypes);
        roomTypeComboBox .addActionListener(e -> updateQueueDisplay());

        String[] dates = {
                "All Dates",
                "11/01/24 to 11/08/24",
                "11/08/24 to 11/15/24",
                "11/15/24 to 11/22/24",
                "11/22/24 to 11/29/24",
                "11/29/24 to 12/06/24",
                "12/06/24 to 12/13/24",
                "12/13/24 to 12/20/24",
                "12/20/24 to 12/27/24"
        };
        dateComboBox = new JComboBox<>(dates);
        dateComboBox.addActionListener(e -> updateQueueDisplay());

        JLabel lblHeader = new JLabel("Customer Booking Queue", JLabel.CENTER);
        lblHeader.setFont(new Font("Courier New", Font.BOLD, 40));
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setOpaque(false);
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnClose = createStyledButton("Back");
        btnClose.addActionListener(e -> {
            dispose();
            new WelcomeForm().setVisible(true);
        });

        JPanel filterPanel = new JPanel();
        filterPanel.setOpaque(false);
        filterPanel.add(searchField);
        filterPanel.add(roomTypeComboBox);
        filterPanel.add(dateComboBox);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setOpaque(false);
        footerPanel.add(btnClose);

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(new BorderLayout());
        gradientPanel.add(lblHeader, BorderLayout.NORTH);
        gradientPanel.add(filterPanel, BorderLayout.NORTH);
        gradientPanel.add(scrollPane, BorderLayout.CENTER);
        gradientPanel.add(footerPanel, BorderLayout.SOUTH);

        add(gradientPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(170, 70));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(new Color(0, 0, 0, 150)); 
        button.setFont(new Font("Courier New", Font.BOLD, 15));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 0, 100));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 0, 150)); 
            }
        });

        return button;
    }

    private void loadCustomerData() {
        updateQueueDisplay();
    }

    private void updateQueueDisplay() {
        tableModel.setRowCount(0); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); 
        String searchText = searchField.getText().toLowerCase();
        String selectedRoomType = (String) roomTypeComboBox.getSelectedItem();
        String selectedDate = (String) dateComboBox.getSelectedItem();

        LocalDate startDate = null;
        LocalDate endDate = null;

        if (!selectedDate.equals("All Dates")) {
            String[] dateRange = selectedDate.split(" to ");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
            startDate = LocalDate.parse(dateRange[0], dateFormatter);
            endDate = LocalDate.parse(dateRange[1], dateFormatter);
        }

        List<Customer> customers = CustomerManager.getCustomers();

        for (Customer customer : customers) {
            boolean matchesSearch = customer.getName().toLowerCase().contains(searchText);
            boolean matchesRoomType = selectedRoomType.equals("All Rooms") ||
                                      getRoomTypeCode(customer.getRoomDetails()).equals(selectedRoomType);

            boolean matchesDate = true;
            if (startDate != null && endDate != null) {
                LocalDate checkInDate = customer.getCheckInDate();
                LocalDate checkOutDate = customer.getCheckOutDate();

                matchesDate = (checkInDate.isBefore(endDate) && checkOutDate.isAfter(startDate));
            }

            if (matchesSearch && matchesRoomType && matchesDate) {
                String bookingTimeStr = customer.getCheckInDate() != null ?
                                        customer.getCheckInDate().format(formatter) : "N/A"; 
                tableModel.addRow(new Object[]{
                    customer.getTransactionNumber(),
                    customer.getName(),
                    customer.getDays(),
                    getRoomTypeCode(customer.getRoomDetails()),
                    bookingTimeStr
                });
            }
        }
    }


    private String getRoomTypeCode(String roomDetails) {
        if (roomDetails == null) {
            return ""; 
        }
        switch (roomDetails) {
            case "Standard Room A":
                return "ST101";
            case "Standard Room B":
                return "ST102";
            case "Standard Room C":
                return "ST103";
            case "Twin Room A":
                return "TW101";
            case "Twin Room B":
                return "TW102";
            case "Twin Room C":
                return "TW103";
            case "Family Room A":
                return "FR101";
            case "Family Room B":
                return "FR102";
            case "Family Room C":
                return "FR103";
            case "Promo Room A":
                return "PR101";
            case "Promo Room B":
                return "PR102";
            case "Promo Room C":
                return "PR103";
            default:
                return "";
        }
    }

    class GradientPanel extends JPanel {
        private Image backgroundImage;

        public GradientPanel() {
            try {
                URL imageUrl = getClass().getResource("/Aurum Manila.jpg");
                if (imageUrl != null) {
                    backgroundImage = Toolkit.getDefaultToolkit().getImage(imageUrl);
                } else {
                    System.err.println("Image not found: /Aurum Manila.jpg");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QueueDisplayForm().setVisible(true));
    }
}