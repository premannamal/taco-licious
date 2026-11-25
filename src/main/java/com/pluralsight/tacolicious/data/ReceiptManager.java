package com.pluralsight.tacolicious.data;

import com.pluralsight.tacolicious.models.Order;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {
    private static final String RECEIPT_FOLDER = "receipts";

    public void saveReceipt(Order order) {
        File folder = new File(RECEIPT_FOLDER);

        //If the folder does not exist, create it.
        if (!folder.exists()) {
            folder.mkdirs();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String fileName = order.getTimestamp().format(formatter) + ".txt";
        File receiptFile = new File(folder, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile))) {
            writer.write(order.getOrderSummary());
            System.out.println("Receipt saved successfully to: " + receiptFile.getPath());
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}
