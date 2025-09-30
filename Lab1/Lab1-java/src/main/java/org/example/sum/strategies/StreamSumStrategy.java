package org.example.sum.strategies;

import java.util.Arrays;

public class StreamSumStrategy implements SumStrategy {
    @Override
    public long calcSum(long[] arr) {
        return Arrays.stream(arr).parallel().sum();
    }
}
