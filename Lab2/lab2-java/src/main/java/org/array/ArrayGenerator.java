package org.array;

import java.util.Random;

public class ArrayGenerator {

    private final Random rand;

    public ArrayGenerator() {
        rand = new Random();
    }

    public long[] generateArray(int arrLength, int max) {
        long[] arr = new long[arrLength];
        for (int i = 0; i < arrLength; i++) {
            arr[i] = rand.nextInt(max);
        }
        return arr;
    }
}
