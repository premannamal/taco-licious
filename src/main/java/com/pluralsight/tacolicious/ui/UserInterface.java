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
}