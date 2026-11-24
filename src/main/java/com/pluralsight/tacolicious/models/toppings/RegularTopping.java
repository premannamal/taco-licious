package com.pluralsight.tacolicious.models.toppings;

public class RegularTopping extends Topping {
    private String category; // Regular, Sauce, or Side

    //Constructor
    public RegularTopping(String name, String category, boolean isExtra) {
        super(name, isExtra);
        this.category = category;
    }

    //gettor
    public String getCategory() {
        return category;
    }

    //No additional price for regular
    @Override
    public double getPrice(String tacoSize) {
        return 0.00; // Regular toppings, sauces, and sides are included
    }
}
