package commands

import (
	"fmt"
	summanager "lab1-go/internal/manager"
	slicegenerator "lab1-go/internal/slicegen"
	"lab1-go/internal/strategies"

	"github.com/spf13/cobra"
)

func RegisterStrategyCommands(rootCmd *cobra.Command) {
	var (
		numGoroutines int
		numOfTasks    int
	)

	register := func(name string, constructor func(int, int) strategies.SumStrategy) {
		cmd := &cobra.Command{
			Use:   name,
			Short: fmt.Sprintf("%s sum strategy", name),
			Run: func(cmd *cobra.Command, args []string) {
				arr := slicegenerator.Generate(100_000_000, 100_000)
				strategy := constructor(numGoroutines, numOfTasks)

				timed := strategies.NewTimingSumStrategyDecorator(strategy)
				sum := timed.CalcSum(arr)
				fmt.Println("Sum:", sum)
			},
		}
		cmd.Flags().IntVarP(&numGoroutines, "goroutines", "g", 4, "Number of concurrent goroutines")
		cmd.Flags().IntVarP(&numOfTasks, "tasks", "t", 1000, "Number of tasks to split the work into")
		rootCmd.AddCommand(cmd)
	}

	register("regular", func(numGoroutines, _ int) strategies.SumStrategy {
		sumManager := summanager.New()
		return strategies.NewRegularSumStrategy(sumManager, numGoroutines)
	})

	register("single", func(_, _ int) strategies.SumStrategy {
		sumManager := summanager.New()
		return strategies.NewSingleGouroutineSumStrategy(sumManager)
	})

	register("worker-pool", func(numGoroutines, numOfTasks int) strategies.SumStrategy {
		sumManager := summanager.New()
		return strategies.NewWorkerPoolSumStrategy(sumManager, numGoroutines, numOfTasks)
	})
}
