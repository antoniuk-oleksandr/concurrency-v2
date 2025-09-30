package org.example.sum.strategies;

public record TimingSumStrategyDecorator(SumStrategy wrapped) implements SumStrategy {

    @Override
    public long calcSum(long[] arr) {
        long start = System.currentTimeMillis();
        long result = wrapped.calcSum(arr);
        long end = System.currentTimeMillis();
        System.out.printf("Time elapsed: %dms\n", end - start);
        return result;
    }
}
