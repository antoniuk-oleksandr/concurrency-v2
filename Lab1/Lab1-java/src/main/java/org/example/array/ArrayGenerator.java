package org.example.array;


import java.util.Random;
import java.util.stream.IntStream;

public class ArrayGenerator {
    private final Random rand;

    public ArrayGenerator() {
        rand = new Random();
    }

    public int[] generateArray(int arrLength, int max) {
        int[] arr = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            arr[i] = rand.nextInt(max);
        }
        return arr;
    }
}
