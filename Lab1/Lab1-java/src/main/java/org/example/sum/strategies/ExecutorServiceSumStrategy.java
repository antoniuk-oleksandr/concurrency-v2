package org.example.sum.strategies;

import org.example.sum.managers.SumManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public record ExecutorServiceSumStrategy(SumManager sumManager, int numOfThreads) implements SumStrategy {
    @Override
    public long calcSum(long[] arr) {
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        List<Callable<Void>> tasks = new ArrayList<>(numOfThreads);

        long[] subSums = new long[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            int finalI = i;
            tasks.add(() -> {
                int[] boundaries = sumManager.defineSubArrBoundaries(finalI);
                long sum = sumManager.calcSubSum(boundaries);
                subSums[finalI] = sum;
                return null;
            });
        }

        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executor.shutdown();
        }


        return Arrays.stream(subSums).sum();
    }
}
