package strategies

import (
	sumManager "lab1-go/internal/manager"
	"sync"
)

type regularSumStrategy struct {
	sumManager      sumManager.SumManager
	numOfGoroutines int
}

func NewRegularSumStrategy(sumManager sumManager.SumManager, numOfGoroutines int) SumStrategy {
	return regularSumStrategy{
		sumManager:      sumManager,
		numOfGoroutines: numOfGoroutines,
	}
}

func (r regularSumStrategy) CalcSum(arr []int) int64 {
	ch := make(chan int64, r.numOfGoroutines)
	var wg sync.WaitGroup
	wg.Add(r.numOfGoroutines)

	for i := range r.numOfGoroutines {
		go func(i int) {
			defer wg.Done()

			boundaries := r.sumManager.CalcSubArrBoundaries(arr, r.numOfGoroutines, i)
			ch <- r.sumManager.CalculateSum(arr, boundaries)
		}(i)
	}

	wg.Wait()
	close(ch)

	sum := int64(0)
	for val := range ch {
		sum += val
	}

	return sum
}
