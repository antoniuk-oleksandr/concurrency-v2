package main

import (
	"conc-lab-2/internal/calculator"
	"conc-lab-2/internal/slicegen"
	"conc-lab-2/internal/timer"
)

func main() {
	arr := slicegen.Generate(10_000_000, 10_000)

	arrCopy := make([]int64, len(arr))
	copy(arrCopy, arr)

	single := calculator.NewSingleCalculator()
	singleTimer := timer.NewTimer(single)
	sum := singleTimer.CalcSum(arrCopy)
	println("Sum (single)", sum, "\n")

	arrCopy = make([]int64, len(arr))
	copy(arrCopy, arr)

	parallel := calculator.NewParallelCalculator(4)
	parallelTimer := timer.NewTimer(parallel)
	sumParallel := parallelTimer.CalcSum(arrCopy)
	println("Sum (parallel):", sumParallel, "\n")
}
