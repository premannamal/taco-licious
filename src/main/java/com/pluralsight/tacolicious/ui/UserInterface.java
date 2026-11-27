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
            System.out.println("\n*** WELCOME TO TACO-LICIOUS ***");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    currentOrder = new Order();
                    displayOrderScreen();
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
            System.out.println("\n*** CURRENT ORDER ***");
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
                    processAddTaco();
                    break;
                case "2":
                    processAddDrink();
                    break;
                case "3":
                    processAddChipsAndSalsa();
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
        boolean validChoice = false;
        System.out.println("\n*** ADD TACO ***");
        System.out.println("1) Custom Taco");
        System.out.println("2) Signature: Street Tacos");
        System.out.println("3) Signature: Super Burrito");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine().trim();
        Taco taco = null;

        do {
            validChoice = true;
            switch (choice) {
                case "2":
                    taco = new com.pluralsight.tacolicious.models.signatures.StreetTacos();
                    customizeSignatureTaco(taco);
                    break;
                case "3":
                    taco = new com.pluralsight.tacolicious.models.signatures.SuperBurrito();
                    customizeSignatureTaco(taco);
                    break;
                case "1":
                    String size = promptChoice("Select Size", new String[]{"Single", "3-Taco", "Burrito"});
                    String shell = promptChoice("Select Shell", new String[]{"Corn", "Flour", "Hard Shell", "Bowl"});
                    taco = new Taco(size, shell);
                    addAllToppingsFlow(taco);
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
                    validChoice = false; // Keeps the loop running
                    break;
            }
        } while (!validChoice);

        currentOrder.addItem(taco);
        System.out.println("Taco added to your order!");
    }

    private void customizeSignatureTaco(Taco taco) {
        boolean customizing = true;
        while (customizing) {
            System.out.println("\nCurrent Signature Taco:");
            System.out.println(taco.getDescription());
            System.out.println("\n1) Add More Toppings");
            System.out.println("2) Remove a Topping");
            System.out.println("3) Toggle Covered in Salsa & Queso (Currently: " + (taco.isCovered() ? "Yes" : "No") + ")");
            System.out.println("0) Done Customizing");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addAllToppingsFlow(taco);
                    break;
                case "2":
                    removeToppingFlow(taco);
                    break;
                case "3":
                    taco.setCovered(!taco.isCovered());
                    System.out.println("Updated covered status.");
                    break;
                case "0":
                    customizing = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void removeToppingFlow(Taco taco) {
        if (taco.getToppings().isEmpty()) {
            System.out.println("No toppings to remove.");
            return;
        }
        System.out.println("\nSelect a topping to remove (or 0 to cancel):");
        for (int i = 0; i < taco.getToppings().size(); i++) {
            System.out.println((i + 1) + ") " + taco.getToppings().get(i));
        }
        System.out.print("Choice: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (index == -1) return;
            if (taco.removeTopping(index)) {
                System.out.println("Topping removed.");
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }


    private void addToppingsLoop(Taco taco, String category, String[] options)
    {
        boolean isExtra;
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

                    if ( category.equals("Meat") || category.equals("Cheese") )
                    {
                        while (true)
                        {
                            System.out.print("Would you like extra " + selectedName + "? (Y/N): ");
                            input = scanner.nextLine().trim();
                            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N"))
                            {
                                isExtra = input.equalsIgnoreCase("Y")
                                break; // Valid input ('y', 'Y', 'n', or 'N')
                            } else
                            {
                                System.out.println("Invalid input. Please enter Y or N.");
                            }
                        }
                    }
                    else
                    {
                        isExtra = false;
                    }

                    if (category.equals("Meat")) {
                        taco.addTopping(new MeatTopping(selectedName, isExtra));
                    } else if (category.equals("Cheese")) {
                        taco.addTopping(new CheeseTopping(selectedName, isExtra));
                    }
                    /*
                    else {
                        taco.addTopping(new RegularTopping(selectedName, category, isExtra));
                    }
                    */
                    if ( category.equals("Meat") || category.equals("Cheese") )
                    {
                        System.out.println("Added " + selectedName + (isExtra ? " (Extra)" : ""));
                    }
                } else {
                    System.out.println("Invalid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void addAllToppingsFlow(Taco taco) {
        addToppingsLoop(taco, "Meat", new String[]{
                "Carne Asada", "Al Pastor", "Carnitas", "Pollo", "Chorizo", "Pescado"
        });
        addToppingsLoop(taco, "Cheese", new String[]{
                "Queso Fresco", "Oaxaca", "Cotija", "Cheddar"
        });
        addToppingsLoop(taco, "Regular", new String[]{
                "Lettuce", "Cilantro", "Onions", "Tomatoes", "Jalapeños",
                "Radishes", "Pico de Gallo", "Guacamole", "Corn"
        });
        addToppingsLoop(taco, "Sauce", new String[]{
                "Salsa Verde", "Salsa Roja", "Chipotle", "Habanero", "Extra Hot"
        });
        addToppingsLoop(taco, "Side", new String[]{
                "Lime Wedges", "Crema"
        });

        // Only prompt for "covered" automatically if building a custom Taco from scratch
        if (taco.getClass().equals(Taco.class)) {
            System.out.print("Would you like it covered in salsa and queso? (Y/N): ");
            taco.setCovered(scanner.nextLine().trim().equalsIgnoreCase("Y"));
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

    private void processAddDrink() {
        System.out.println("\n*** ADD DRINK ***");
        String size = promptChoice("Select Drink Size", new String[]{"Small", "Medium", "Large"});
        String flavor = promptChoice("Select Flavor", new String[]{"Horchata", "Jamaica", "Coca-Cola", "Sprite", "Water"});

        currentOrder.addItem(new Drink(size, flavor));
        System.out.println("Drink added to order!");
    }

    private void processAddChipsAndSalsa() {
        System.out.println("\n*** ADD CHIPS & SALSA ***");
        String salsa = promptChoice("Select Salsa Type", new String[]{"Salsa Verde", "Salsa Roja", "Pico de Gallo", "Habanero"});

        currentOrder.addItem(new ChipsAndSalsa(salsa));
        System.out.println("Chips & Salsa added to order!");
    }

}