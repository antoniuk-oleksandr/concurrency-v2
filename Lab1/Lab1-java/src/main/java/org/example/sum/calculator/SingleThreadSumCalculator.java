package org.example.sum.calculator;

import org.example.sum.manager.SumManager;

public record SingleThreadSumCalculator(SumManager sumManager) implements SumCalculator {
    @Override
    public long calcSum(int[] arr) {
        int[] boundaries = new int[]{0, arr.length};
        return sumManager.calcSubSum(boundaries);
    }
}
