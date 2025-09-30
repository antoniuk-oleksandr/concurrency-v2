package org.example.sum.factories;

import org.example.sum.strategies.SingleThreadSumStrategy;
import org.example.sum.strategies.SumStrategy;
import org.example.sum.managers.SumManager;

public class SingleThreadSumStrategyFactory implements SumStrategyFactory {
    @Override
    public SumStrategy createStrategy(long[] arr) {
        SumManager sumManager = new SumManager(arr, 1);
        return new SingleThreadSumStrategy(sumManager);
    }
}
