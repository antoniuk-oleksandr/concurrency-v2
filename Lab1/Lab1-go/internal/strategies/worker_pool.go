package strategies

import (
	sumManager "lab1-go/internal/manager"
	"sync"

	"github.com/panjf2000/ants/v2"
)

type workerPoolSumStrategy struct {
	numOfWorkers int
	sumManager   sumManager.SumManager
	numOfTasks   int
}

func NewWorkerPoolSumStrategy(sumManager sumManager.SumManager, numOfWorkers, numOfTasks int) SumStrategy {
	return workerPoolSumStrategy{
		numOfWorkers: numOfWorkers,
		sumManager:   sumManager,
		numOfTasks:   numOfTasks,
	}
}

func (w workerPoolSumStrategy) CalcSum(arr []int) int64 {
	results := make(chan int64, w.numOfTasks)
	var wg sync.WaitGroup
	wg.Add(w.numOfTasks)

	pool, _ := ants.NewPoolWithFunc(w.numOfWorkers, func(i any) {
		defer wg.Done()
		idx := i.(int)

		boundaries := w.sumManager.CalcSubArrBoundaries(arr, w.numOfTasks, idx)
		sum := w.sumManager.CalculateSum(arr, boundaries)
		results <- sum
	})
	defer pool.Release()

	for i := range w.numOfTasks {
		_ = pool.Invoke(i)
	}

	func() {
		wg.Wait()
		close(results)
	}()

	var total int64
	for s := range results {
		total += s
	}

	return total
}
