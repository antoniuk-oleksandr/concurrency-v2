package org.example.sum.factory;


import org.example.sum.calculator.*;
import org.example.sum.manager.SumManager;
import org.example.sum.strategy.Strategy;

public class SumCalculatorFactory {
    public SumCalculator create(Strategy strategy, int numOfThreads, SumManager sumManager) {
        return switch (strategy) {
            case EXECUTOR -> new ExecutorSumCalculator(numOfThreads, sumManager);
            case STREAM -> new StreamSumCalculator();
            case FORK_JOIN -> new ForkJoinSumCalculator(numOfThreads, sumManager);
            case SINGLE -> new SingleThreadSumCalculator(sumManager);
        };
    }
}
