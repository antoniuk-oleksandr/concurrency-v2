package org.example.sum.manager;

public class SumManager {
    private final int step;
    private final int[] arr;

    public SumManager(int[] arr, int numOfThreads) {
        this.arr = arr;
        this.step = arr.length / numOfThreads;
    }

    public int[] defineSubArrBoundaries(int index) {
        int firstIndex = step * index;
        int lastIndex = firstIndex + step;

        return new int[]{firstIndex, lastIndex};
    }

    public long calcSubSum(int[] boundaries) {
        long sum = 0;
        for (int i = boundaries[0]; i < boundaries[1]; i++) {
            sum += arr[i];
        }

        return sum;
    }
}