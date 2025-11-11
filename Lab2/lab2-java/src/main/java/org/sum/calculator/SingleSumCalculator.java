package org.sum.calculator;

public class SingleSumCalculator implements SumCalculator {

    @Override
    public long calcSum(long[] arr) {
        int active = arr.length;
        while (active > 1) {
            int half = active / 2;
            for (int i = 0; i < half; i++) {
                int j = active - 1 - i;
                arr[i] += arr[j];
            }

            active = (active + 1) / 2;
        }

        return arr[0];
    }
}
