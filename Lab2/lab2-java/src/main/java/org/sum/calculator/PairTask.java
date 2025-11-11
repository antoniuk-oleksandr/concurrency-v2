package org.sum.calculator;

import java.util.concurrent.RecursiveAction;

public class PairTask extends RecursiveAction {
    private final long[] arr;
    private final int i;
    private final int active;

    PairTask(long[] arr, int i, int active) {
        this.arr = arr;
        this.i = i;
        this.active = active;
    }

    @Override
    protected void compute() {
        int j = active - 1 - i;
        arr[i] += arr[j];
    }
}