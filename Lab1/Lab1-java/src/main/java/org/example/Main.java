package org.example;

import org.example.array.ArrayGenerator;
import org.example.sum.calculator.*;
import org.example.sum.factory.SumCalculatorFactory;
import org.example.sum.manager.SumManager;
import org.example.sum.strategy.Strategy;
import org.example.sum.timer.TimingSumCalculatorDecorator;
import picocli.CommandLine;


@CommandLine.Command(name = "sum-app", mixinStandardHelpOptions = true)
public class Main implements Runnable {
    @CommandLine.Parameters(
            index = "0",
            description = "Strategy: stream, executor, fork-join or single"
    )
    private Strategy strategy;

    @CommandLine.Option(
            names = {"-t", "--threads"},
            description = "Number of threads (default: 1)"
    )
    private int numOfThreads = 1;

    @CommandLine.Option(
            names = {"-l", "--length"},
            description = "Length of array (default: 100,000,000)"
    )
    private int arrLength = 100_000_000;

    @CommandLine.Option(
            names = {"-m", "--max"},
            description = "Maximum value in array (default: 10,000)"
    )
    private int arrMax = 10_000;

    @Override
    public void run() {
        ArrayGenerator arrayManager = new ArrayGenerator();
        int[] arr = arrayManager.generateArray(arrLength, arrMax);

        SumManager sumManager = new SumManager(arr, this.numOfThreads);

        SumCalculatorFactory sumCalculatorFactory = new SumCalculatorFactory();
        SumCalculator sumCalculator = sumCalculatorFactory.create(strategy, numOfThreads, sumManager);

        SumCalculator calculatorDecorator = new TimingSumCalculatorDecorator(sumCalculator);

        long result = calculatorDecorator.calcSum(arr);
        System.out.println("Result: " + result);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
