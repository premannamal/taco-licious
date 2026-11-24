package com.pluralsight.tacolicious.models.toppings;

public class MeatTopping extends Topping {

    //constructor
    public MeatTopping(String name, boolean isExtra) {
        //call super classes constructor
        super(name, isExtra);
    }

    @Override
    public double getPrice(String tacoSize) {
        double basePrice;
        double extraPrice;

        switch (tacoSize.toLowerCase()) {
            case "3-taco":
                basePrice = 2.00;
                extraPrice = 1.00;
                break;
            case "burrito":
                basePrice = 3.00;
                extraPrice = 1.50;
                break;
            case "single":
            default:
                basePrice = 1.00;
                extraPrice = 0.50;
                break;
        }
        return isExtra() ? (basePrice + extraPrice) : basePrice;
    }
}
