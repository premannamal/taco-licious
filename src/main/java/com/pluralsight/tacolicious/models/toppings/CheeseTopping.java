package com.pluralsight.tacolicious.models.toppings;

public class CheeseTopping extends Topping {

    //Constructor
    public CheeseTopping(String name, boolean isExtra) {
        super(name, isExtra);
    }

    @Override
    public double getPrice(String tacoSize) {
        double basePrice;
        double extraPrice;

        switch (tacoSize.toLowerCase()) {
            case "3-taco":
                basePrice = 1.50;
                extraPrice = 0.60;
                break;
            case "burrito":
                basePrice = 2.25;
                extraPrice = 0.90;
                break;
            case "single":
            default:
                basePrice = 0.75;
                extraPrice = 0.30;
                break;
        }
        return isExtra() ? (basePrice + extraPrice) : basePrice;
    }
}
