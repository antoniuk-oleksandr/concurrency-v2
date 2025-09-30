package org.example;

import org.example.array.ArrayGenerator;
import org.example.sum.factories.*;
import org.example.sum.strategies.SumStrategy;
import org.example.sum.strategies.TimingSumStrategyDecorator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ArrayGenerator arrayManager = new ArrayGenerator();
        long[] arr = arrayManager.generateArray(100_000_000, 100_00);

        Map<Integer, SumStrategyFactory> factories = createFactories(scanner);

        SumStrategyFactory strategyFactory = selectFactory(scanner, factories);
        SumStrategy strategy = strategyFactory.createStrategy(arr);

        SumStrategy timingStrategy = new TimingSumStrategyDecorator(strategy);
        long sum = timingStrategy.calcSum(arr);
        System.out.printf("Sum: %d\n", sum);
    }

    private static Map<Integer, SumStrategyFactory> createFactories(Scanner scanner) {
        Map<Integer, SumStrategyFactory> factories = new HashMap<>();
        factories.put(1, new ExecutorServiceSumStrategyFactory(scanner));
        factories.put(2, new StreamSumStrategyFactory());
        factories.put(3, new ForkJoinSumStrategyFactory(scanner));
        factories.put(4, new SingleThreadSumStrategyFactory());
        return factories;
    }

    private static SumStrategyFactory selectFactory(Scanner scanner, Map<Integer, SumStrategyFactory> factories) throws Exception {
        System.out.println("""
                Choose strategy:
                1. ExecutorService  
                2. Streams
                3. ForkJoin
                4. SingleThread
                Your choice:"""
        );

        int choice = scanner.nextInt();
        SumStrategyFactory factory = factories.get(choice);

        if (factory == null) {
            throw new Exception("Invalid choice!");
        }

        return factory;
    }
}
