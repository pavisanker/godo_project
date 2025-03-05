package com.GoDo.godo.utilities;

import java.util.Random;

public class CustomIdGenerator {
    public static String generateCustomId() {
        Random random = new Random();

        // Generate 4 random digits
        int digits = 1000 + random.nextInt(9000);

        // Generate 2 random uppercase letters
        char letter1 = (char) ('A' + random.nextInt(26));
        char letter2 = (char) ('A' + random.nextInt(26));

        // Combine digits and letters
        return digits + "" + letter1 + letter2;
    }
}

