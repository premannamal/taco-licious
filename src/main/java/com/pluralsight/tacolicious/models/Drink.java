package com.pluralsight.tacolicious.models;

public class Drink implements Orderable {
    private String size; // Small, Medium, Large
    private String flavor;

    //Constuctor for Drink
    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    //getPrice() method is overwritten
    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "large":
                return 3.00;
            case "medium":
                return 2.50;
            case "small":
            default:
                return 2.00;
        }
    }

    @Override
    public String getDescription() {
        return String.format("%s %s Drink - $%.2f", size, flavor, getPrice());
    }
}
