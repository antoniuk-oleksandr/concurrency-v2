package org.example.sum.factories;

import org.example.sum.strategies.StreamSumStrategy;
import org.example.sum.strategies.SumStrategy;

public class StreamSumStrategyFactory implements SumStrategyFactory {
    @Override
    public SumStrategy createStrategy(long[] arr) {
        return new StreamSumStrategy();
    }
}
