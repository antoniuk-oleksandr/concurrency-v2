package org.example.sum.calculator;

import org.example.sum.manager.SumManager;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10_000;
    private final SumManager sumManager;
    private final int start;
    private final int end;

    public ForkJoinSumTask(SumManager sumManager, int start, int end) {
        this.sumManager = sumManager;
        this.start = start;
        this.end = end;
    }

    @Override
    public Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return sumManager.calcSubSum(new int[]{start, end});
        } else {
            int mid = start + length / 2;
            ForkJoinSumTask leftTask = new ForkJoinSumTask(sumManager, start, mid);
            ForkJoinSumTask rightTask = new ForkJoinSumTask(sumManager, mid, end);

            leftTask.fork();
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();

            return leftResult + rightResult;
        }
    }
}
