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

    private void processAddTaco() {
        System.out.println("\n--- BUILD YOUR TACO ---");
        String size = promptChoice("Select Size", new String[]{"Single", "3-Taco", "Burrito"});
        String shell = promptChoice("Select Shell", new String[]{"Corn", "Flour", "Hard Shell", "Bowl"});

        Taco taco = new Taco(size, shell);

        // 1. Meats
        addToppingsLoop(taco, "Meat", new String[]{
                "Carne Asada", "Al Pastor", "Carnitas", "Pollo", "Chorizo", "Pescado"
        });

        // 2. Cheeses
        addToppingsLoop(taco, "Cheese", new String[]{
                "Queso Fresco", "Oaxaca", "Cotija", "Cheddar"
        });

        // 3. Regular Toppings
        addToppingsLoop(taco, "Regular", new String[]{
                "Lettuce", "Cilantro", "Onions", "Tomatoes", "Jalapeños",
                "Radishes", "Pico de Gallo", "Guacamole", "Corn"
        });
        // 4. Sauces
        addToppingsLoop(taco, "Sauce", new String[]{
                "Salsa Verde", "Salsa Roja", "Chipotle", "Habanero", "Extra Hot"
        });

        // 5. Sides
        addToppingsLoop(taco, "Side", new String[]{
                "Lime Wedges", "Crema"
        });

        // 6. Covered in Salsa & Queso
        System.out.print("Would you like it covered in salsa and queso? (Y/N): ");
        String coveredInput = scanner.nextLine().trim();
        taco.setCovered(coveredInput.equalsIgnoreCase("Y"));

        currentOrder.addItem(taco);
        System.out.println("Taco added to your order!");
    }

    private void addToppingsLoop(Taco taco, String category, String[] options) {
        while (true) {
            System.out.println("\nSelect " + category + " (or 0 to continue):");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.println("0) Done with " + category);
            System.out.print("Choice: ");

            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                break;
            }

            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < options.length) {
                    String selectedName = options[index];
                    System.out.print("Would you like extra " + selectedName + "? (Y/N): ");
                    boolean isExtra = scanner.nextLine().trim().equalsIgnoreCase("Y");

                    if (category.equals("Meat")) {
                        taco.addTopping(new MeatTopping(selectedName, isExtra));
                    } else if (category.equals("Cheese")) {
                        taco.addTopping(new CheeseTopping(selectedName, isExtra));
                    } else {
                        taco.addTopping(new RegularTopping(selectedName, category, isExtra));
                    }
                    System.out.println("Added " + selectedName + (isExtra ? " (Extra)" : ""));
                } else {
                    System.out.println("Invalid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

}

    private String promptChoice(String title, String[] options) {
        while (true) {
            System.out.println("\n" + title + ":");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }
            System.out.print("Choice: ");
            try {
                int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (index >= 0 && index < options.length) {
                    return options[index];
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid selection. Please try again.");
        }
    }
}

        private boolean processCheckout() {
        if (!currentOrder.isValidOrder()) {
            System.out.println("\n[!] Invalid Order: If you order 0 tacos, you must purchase at least one drink or chips & salsa.");
            return false;
        }



        System.out.println("\n" + currentOrder.getOrderSummary());
        System.out.println("1) Confirm Order");
        System.out.println("2) Cancel Order");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine().trim();
        if (choice.equals("1")) {
            receiptManager.saveReceipt(currentOrder);
            System.out.println("Order confirmed! Returning to Home Screen.");
            return true;
        } else if (choice.equals("2")) {
            System.out.println("Order canceled. Returning to Home Screen.");
            return true;
        } else {
            System.out.println("Invalid option. Returning to Order Screen.");
            return false;
        }
    }

}