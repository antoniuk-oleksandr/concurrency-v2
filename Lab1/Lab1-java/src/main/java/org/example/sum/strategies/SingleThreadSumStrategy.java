package org.example.sum.strategies;

import org.example.sum.managers.SumManager;

public record SingleThreadSumStrategy(
        SumManager sumManager
) implements SumStrategy {
    @Override
    public long calcSum(long[] arr) {
        int[] boundaries = new int[]{0, arr.length};
        return sumManager.calcSubSum(boundaries);
    }
}
