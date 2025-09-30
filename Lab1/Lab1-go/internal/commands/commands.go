package commands

import (
	"fmt"
	slicegenerator "lab1-go/internal/slicegen"
	summanager "lab1-go/internal/manager"
	"lab1-go/internal/strategies"

	"github.com/spf13/cobra"
)

func RegisterStrategyCommands(rootCmd *cobra.Command) {
	var numGoroutines int

	register := func(name string, constructor func(int) strategies.SumStrategy) {
		cmd := &cobra.Command{
			Use:   name,
			Short: fmt.Sprintf("%s sum strategy", name),
			Run: func(cmd *cobra.Command, args []string) {
				arr := slicegenerator.Generate(100_000_000, 100_000)
				strategy := constructor(numGoroutines)

				timed := strategies.NewTimingSumStrategyDecorator(strategy)
				sum := timed.CalcSum(arr)
				fmt.Println("Sum:", sum)
			},
		}
		cmd.Flags().IntVarP(&numGoroutines, "goroutines", "g", 4, "Number of concurrent goroutines")
		rootCmd.AddCommand(cmd)
	}

	register("regular", func(n int) strategies.SumStrategy {
		sumManager := summanager.New()
		return strategies.NewRegularSumStrategy(sumManager, n)
	})

	register("single", func(_ int) strategies.SumStrategy {
		sumManager := summanager.New()
		return strategies.NewSingleGouroutineSumStrategy(sumManager)
	})
	
	register("worker-pool", func(n int) strategies.SumStrategy {
		sumManager := summanager.New()
		return strategies.NewWorkerPoolSumStrategy(sumManager, n)
	})
}
