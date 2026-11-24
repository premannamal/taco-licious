package com.pluralsight.tacolicious.models.toppings;

public abstract class Topping {
    private String name;
    private boolean isExtra;

    //Constructor method for Topping class. Since this is an abstract class, there won't be any objects for it
    public Topping(String name, boolean isExtra) {
        this.name = name;
        this.isExtra = isExtra;
    }

    //gettor method for name
    public String getName() {
        return name;
    }

    //gettor method for isExtra
    public boolean isExtra() {
        return isExtra;
    }

    //Since this is an abstract method, it does not require a body in this class.
    //The subclasses where this method is called will have the body.
    public abstract double getPrice(String tacoSize);

    @Override
    public String toString() {
        return isExtra ? name + " (Extra)" : name;
    }
}
