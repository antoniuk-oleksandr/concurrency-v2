package org.example.sum.factories;

import org.example.sum.strategies.SumStrategy;

public interface SumStrategyFactory {
    SumStrategy createStrategy(long[] arr);
}
