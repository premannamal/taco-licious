package com.pluralsight.tacolicious.ui;

import com.pluralsight.tacolicious.data.ReceiptManager;
import com.pluralsight.tacolicious.models.*;
import com.pluralsight.tacolicious.models.toppings.*;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Order currentOrder;
    private ReceiptManager receiptManager;

    //Constructor method
    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.receiptManager = new ReceiptManager();
    }

    public void displayHomeScreen() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== WELCOME TO TACO-LICIOUS ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    currentOrder = new Order();
                    //I NEED TO CREATE displayOrderScreen() and uncomment the following line
                    //displayOrderScreen();
                    break;
                case "0":
                    System.out.println("Thank you for visiting TACO-licious! Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayOrderScreen() {
        boolean ordering = true;
        while (ordering) {
            System.out.println("\n--- CURRENT ORDER ---");
            if (currentOrder.getItems().isEmpty()) {
                System.out.println("Cart is currently empty.");
            } else {
                for (Orderable item : currentOrder.getItems()) {
                    System.out.println("- " + item.getDescription());
                }
                System.out.printf("Current Subtotal: $%.2f%n", currentOrder.getTotal());
            }

            System.out.println("\nOrder Menu:");
            System.out.println("1) Add Taco");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips & Salsa");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    //processAddTaco();
                    break;
                case "2":
                    //processAddDrink();
                    break;
                case "3":
                    //processAddChipsAndSalsa();
                    break;
                case "4":
                    if (processCheckout()) {
                        ordering = false;
                    }
                    break;
                case "0":
                    System.out.println("Order canceled. Returning to Home Screen.");
                    currentOrder = null;
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

}