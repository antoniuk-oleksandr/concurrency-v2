package org.sum.calculator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public record ExecutorSumCalculator(int numOfWorkers) implements SumCalculator {

    @Override
    public long calcSum(long[] arr) {
        ExecutorService pool = Executors.newFixedThreadPool(numOfWorkers);

        int active = arr.length;
        while (active > 1) {
            int half = active / 2;
            CountDownLatch latch = new CountDownLatch(half);

            for (int i = 0; i < half; i++) {
                final int index = i;
                int finalActive = active;
                pool.execute(() -> {
                    int j = finalActive - 1 - index;
                    arr[index] += arr[j];
                    latch.countDown();
                });
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            active = (active + 1) / 2;
        }

        pool.shutdown();

        return arr[0];
    }
}
