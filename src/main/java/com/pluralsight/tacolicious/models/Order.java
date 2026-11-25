package com.pluralsight.tacolicious.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Orderable> items;
    private LocalDateTime timestamp;

    //Default Constructor
    public Order() {
        this.items = new ArrayList<>();
        this.timestamp = LocalDateTime.now();
    }

    public void addItem(Orderable item) {
        items.add(item); // Maintains insertion order (most recent last)
    }

    //gettor method for items
    public List<Orderable> getItems() {
        return items;
    }

    //gettor methods for timestamp
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getTotal() {
        //initialize the total variable
        double total = 0.0;
        for (Orderable item : items) {
            //accumlate the price in total variable
            total = total + item.getPrice();
        }
        return total;
    }

    // Rule: If 0 tacos are ordered, customer must purchase chips & salsa or a drink
    public boolean isValidOrder() {
        if (items.isEmpty()) {
            return false;
        }
        boolean hasTaco = items.stream().anyMatch(item -> item instanceof Taco);
        boolean hasSideOrDrink = items.stream()
                .anyMatch(item -> item instanceof Drink || item instanceof ChipsAndSalsa);

        return hasTaco || hasSideOrDrink;
    }

    public String getOrderSummary() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("          TACO-LICIOUS RECEIPT          \n");
        sb.append("Date: ").append(timestamp.format(formatter)).append("\n");
        sb.append("========================================\n");

        if (items.isEmpty()) {
            sb.append("(No items in order)\n");
        } else {
            for (int j = 0; j < items.size(); j++) {
                sb.append(j + 1).append(". ").append(items.get(j).getDescription()).append("\n");
            }
        }

        sb.append("----------------------------------------\n");
        sb.append(String.format("TOTAL: $%.2f\n", getTotal()));
        sb.append("========================================\n");
        return sb.toString();
    }
}
