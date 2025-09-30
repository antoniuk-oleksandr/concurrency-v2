package strategies

import (
	sumManager "lab1-go/internal/manager"
	"sync"

	"github.com/panjf2000/ants/v2"
)

type workerPoolSumStrategy struct {
	numOfWorkers int
	sumManager   sumManager.SumManager
}

func NewWorkerPoolSumStrategy(sumManager sumManager.SumManager, numOfWorkers int) SumStrategy {
	return workerPoolSumStrategy{
		numOfWorkers: numOfWorkers,
		sumManager:   sumManager,
	}
}

func (w workerPoolSumStrategy) CalcSum(arr []int) int64 {
	results := make(chan int64, w.numOfWorkers)
	var wg sync.WaitGroup
	wg.Add(w.numOfWorkers)

	pool, _ := ants.NewPoolWithFunc(w.numOfWorkers, func(i any) {
		defer wg.Done()
		idx := i.(int)

		boundaries := w.sumManager.CalcSubArrBoundaries(arr, w.numOfWorkers, idx)
		sum := w.sumManager.CalculateSum(arr, boundaries)
		results <- sum
	})
	defer pool.Release()

	for i := range w.numOfWorkers {
		_ = pool.Invoke(i)
	}

	wg.Wait()
	close(results)

	var total int64
	for s := range results {
		total += s
	}

	return total
}
