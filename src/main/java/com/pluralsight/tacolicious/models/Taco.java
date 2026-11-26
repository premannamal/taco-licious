package com.pluralsight.tacolicious.models;

import com.pluralsight.tacolicious.models.toppings.Topping;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Taco implements Orderable {
    private String size;  // Single, 3-Taco, Burrito
    private String shell; // corn, flour, hard shell, bowl
    private boolean isCovered;
    private List<Topping> toppings;

    //Constructor
    public Taco(String size, String shell) {
        this.size = size;
        this.shell = shell;
        this.isCovered = false;
        this.toppings = new ArrayList<>();
    }

    public boolean removeTopping(int index) {
        if (index >= 0 && index < toppings.size()) {
            toppings.remove(index);
            return true;
        }
        return false;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }


    public List<Topping> getToppings() {
        return toppings;
    }

    public void setCovered(boolean covered) {
        this.isCovered = covered;
    }

    public boolean isCovered() {
        return isCovered;
    }

    //gettor
    @Override
    public double getPrice() {
        double total;
        switch (size.toLowerCase()) {
            case "3-taco":
                total = 5.00;
                break;
            case "burrito":
                total = 8.50;
                break;
            case "single":
            default:
                total = 3.50;
                break;
        }

        for (Topping topping : toppings) {
            total += topping.getPrice(size);
        }
        return total;
    }

    //gettor


    @Override
    public String getDescription() {
        String title = getClass().equals(Taco.class)
                ? String.format("%s (%s Shell)", size, shell)
                : String.format("%s - %s (%s Shell)", getClass().getSimpleName(), size, shell);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%s - $%.2f",
                title, isCovered ? " [Covered in Salsa & Queso]" : "", getPrice()));

        if (!toppings.isEmpty()) {
            String toppingList = toppings.stream()
                    .map(Topping::toString)
                    .collect(Collectors.joining(", "));
            sb.append("\n   Toppings: ").append(toppingList);
        } else {
            sb.append("\n   Toppings: None");
        }
        return sb.toString();
    }
}

