package org.example.sum.calculator;

import org.example.sum.manager.SumManager;

import java.util.concurrent.ForkJoinPool;

public record ForkJoinSumCalculator(int numOfThreads, SumManager sumManager) implements SumCalculator {
    @Override
    public long calcSum(int[] arr) {
        try (ForkJoinPool pool = new ForkJoinPool(numOfThreads)) {
            ForkJoinSumTask task = new ForkJoinSumTask(sumManager, 0, arr.length);
            return pool.invoke(task);
        }
    }
}
