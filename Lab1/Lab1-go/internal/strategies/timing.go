package strategies

import (
	"fmt"
	"time"
)

type timingSumStrategyDecorator struct {
	wrapped SumStrategy
}

func NewTimingSumStrategyDecorator(wrapped SumStrategy) SumStrategy {
	return timingSumStrategyDecorator{wrapped: wrapped}
}

func (t timingSumStrategyDecorator) CalcSum(arr []int) int64 {
	start := time.Now()
	sum := t.wrapped.CalcSum(arr)
	end := time.Now()
	elapsed := end.Sub(start)
	fmt.Printf("Elapsed time: %.3f ms\n", float64(elapsed.Nanoseconds())/1_000_000)

	return sum
}
