package com.pluralsight.tacolicious.models;

public class ChipsAndSalsa implements Orderable {
    private String salsaType;
    private static final double PRICE = 1.50;

    //Constructor for ChipAndSalsa
    public ChipsAndSalsa(String salsaType)
    {
        this.salsaType = salsaType;
    }

    //Override the getPrice method
    @Override
    public double getPrice()
    {
    //There is only one size available with chips and the type of salsa does not change
    //the price of chips
        return PRICE;
    }

    @Override
    public String getDescription()
    {
        return String.format("Chips & %s Salsa - $%.2f", salsaType, getPrice());
    }
}
