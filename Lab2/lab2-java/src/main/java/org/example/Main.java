package org.example;

import java.util.Arrays;

import org.array.ArrayGenerator;
import org.sum.calculator.ExecutorSumCalculator;
import org.sum.calculator.ForkJoinSumCalculator;
import org.sum.calculator.SingleSumCalculator;
import org.sum.calculator.SumCalculator;
import org.sum.timer.SumTimer;

public class Main {

    public static void main(String[] args) {
        ArrayGenerator generator = new ArrayGenerator();
        long[] arr = generator.generateArray(100_000_000, 10_000);

        long[] arrToUse = Arrays.copyOf(arr, arr.length);

        SumCalculator calculator = new SingleSumCalculator();
        SumCalculator timer = new SumTimer(calculator);
        long result = timer.calcSum(arrToUse);
        System.out.printf("Result (single thread): %d\n", result);

        arrToUse = Arrays.copyOf(arr, arr.length);
        calculator = new ExecutorSumCalculator(6);
        timer = new SumTimer(calculator);
        result = timer.calcSum(arrToUse);
        System.out.printf("Result (executor service): %d\n", result);

        arrToUse = Arrays.copyOf(arr, arr.length);
        calculator = new ForkJoinSumCalculator(6);
        timer = new SumTimer(calculator);
        result = timer.calcSum(arrToUse);
        System.out.printf("Result (fork join): %d\n", result);
    }
}
