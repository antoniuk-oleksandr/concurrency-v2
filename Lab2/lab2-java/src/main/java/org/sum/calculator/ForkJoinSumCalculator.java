package org.sum.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public record ForkJoinSumCalculator(int numOfWorkers) implements SumCalculator {

    @Override
    public long calcSum(long[] arr) {
        try (ForkJoinPool pool = new ForkJoinPool(numOfWorkers)) {
            int active = arr.length;

            while (active > 1) {
                List<PairTask> tasks = new ArrayList<>();
                int half = active / 2;
                for (int i = 0; i < half; i++) {
                    tasks.add(new PairTask(arr, i, active));
                }

                pool.invoke(new WaveTask(tasks));

                active = (active + 1) / 2;
            }
        }

        return arr[0];
    }
}
