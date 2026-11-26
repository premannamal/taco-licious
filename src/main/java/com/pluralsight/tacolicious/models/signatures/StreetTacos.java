package com.pluralsight.tacolicious.models.signatures;

import com.pluralsight.tacolicious.models.Taco;
import com.pluralsight.tacolicious.models.toppings.MeatTopping;
import com.pluralsight.tacolicious.models.toppings.RegularTopping;

public class StreetTacos extends Taco {
    //Constructor method for StreetTacos
    public StreetTacos() {
        super("3-Taco", "Corn");
        addTopping(new MeatTopping("Carne Asada", false));
        addTopping(new RegularTopping("Onions", "Regular", false));
        addTopping(new RegularTopping("Cilantro", "Regular", false));
        addTopping(new RegularTopping("Salsa Verde", "Sauce", false));
        addTopping(new RegularTopping("Lime Wedges", "Side", false));
    }
}
