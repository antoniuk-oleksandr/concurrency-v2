package org.example.sum.strategies;

import org.example.sum.managers.SumManager;

import java.util.concurrent.ForkJoinPool;

public record ForkJoinSumStrategy(
        SumManager sumManager,
        int numOfThreads
) implements SumStrategy {
    @Override
    public long calcSum(long[] arr) {
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);
        ForkJoinSumTask task = new ForkJoinSumTask(sumManager, 0, arr.length);
        return pool.invoke(task);
    }
}
