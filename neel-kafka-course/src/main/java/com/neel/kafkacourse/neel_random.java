package com.neel.kafkacourse;

import java.util.Random;

public class neel_random {
    public static void main(String[] args) {
        Random random = new Random();
        int val1 = random.nextInt(1000000);

        System.out.println(val1);

    }
}
