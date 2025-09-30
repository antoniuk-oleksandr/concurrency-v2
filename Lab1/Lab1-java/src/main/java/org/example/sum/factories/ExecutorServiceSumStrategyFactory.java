package org.example.sum.factories;

import org.example.sum.strategies.ExecutorServiceSumStrategy;
import org.example.sum.strategies.SumStrategy;
import org.example.sum.managers.SumManager;

import java.util.Scanner;

public record ExecutorServiceSumStrategyFactory(Scanner scanner) implements SumStrategyFactory {
    @Override
    public SumStrategy createStrategy(long[] arr) {
        System.out.println("Enter the number of concurrent threads:");
        int numOfThreads = this.scanner.nextInt();

        SumManager sumManager = new SumManager(arr, numOfThreads);
        return new ExecutorServiceSumStrategy(sumManager, numOfThreads);
    }
}
