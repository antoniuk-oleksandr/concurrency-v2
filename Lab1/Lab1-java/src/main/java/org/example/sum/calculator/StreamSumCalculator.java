package org.example.sum.calculator;

import java.util.Arrays;

public class StreamSumCalculator implements SumCalculator {
    @Override
    public long calcSum(int[] arr) {
        return Arrays.stream(arr).parallel().sum();
    }
}
