package com.pluralsight.tacolicious.models.signatures;

import com.pluralsight.tacolicious.models.Taco;
import com.pluralsight.tacolicious.models.toppings.CheeseTopping;
import com.pluralsight.tacolicious.models.toppings.MeatTopping;
import com.pluralsight.tacolicious.models.toppings.RegularTopping;

public class SuperBurrito extends Taco {
    //Constructor method for SuperBurrito
    public SuperBurrito() {
        super("Burrito", "Flour");
        addTopping(new MeatTopping("Carnitas", false));
        addTopping(new CheeseTopping("Cheddar", false));
        addTopping(new RegularTopping("Pico de Gallo", "Regular", false));
        addTopping(new RegularTopping("Lettuce", "Regular", false));
        addTopping(new RegularTopping("Tomatoes", "Regular", false));
        //comment for myself - need to write setCovered method
        setCovered(true);
    }
}
