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
}