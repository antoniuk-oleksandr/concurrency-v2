package timer

import (
	"conc-lab-2/internal/calculator"
	"time"
)

type timer struct {
	calc calculator.Calculator
}

func NewTimer(calc calculator.Calculator) calculator.Calculator {
	return &timer{
		calc: calc,
	}
}

func (t *timer) CalcSum(arr []int64) int64 {
	now := time.Now()
	t.calc.CalcSum(arr)
	after := time.Since(now)
	println("Time:", after.Milliseconds(), "ms")
	return arr[0]
}
