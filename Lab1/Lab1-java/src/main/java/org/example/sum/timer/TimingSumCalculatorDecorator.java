package org.example.sum.timer;

import org.example.sum.calculator.SumCalculator;

public record TimingSumCalculatorDecorator(SumCalculator wrapped) implements SumCalculator {
    @Override
    public long calcSum(int[] arr) {
        long start = System.currentTimeMillis();
        long result = wrapped.calcSum(arr);
        long end = System.currentTimeMillis();
        System.out.printf("Time elapsed: %dms\n", end - start);
        return result;
    }
}
