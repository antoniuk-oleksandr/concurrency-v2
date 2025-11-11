package org.sum.timer;

import org.sum.calculator.SumCalculator;

public class SumTimer implements SumCalculator {
    private final SumCalculator instance;

    public SumTimer(SumCalculator instance) {
        this.instance = instance;
    }

    @Override
    public long calcSum(long[] arr) {
        long before = System.currentTimeMillis();
        long sum = this.instance.calcSum(arr);
        long after = System.currentTimeMillis();
        System.out.printf("Time: %dms\n", after - before);

        return sum;
    }
}
