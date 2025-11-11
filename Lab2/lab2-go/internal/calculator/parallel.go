package calculator

import (
	"sync"
)

type Task struct {
	arr       []int64
	active, i int
	wg        *sync.WaitGroup
}

type parallel struct {
	numOfWorkers int
}

func NewParallelCalculator(numOfWorkers int) Calculator {
	return &parallel{
		numOfWorkers: numOfWorkers,
	}
}

func (p *parallel) CalcSum(arr []int64) int64 {
	ch := make(chan Task)

	for range p.numOfWorkers {
		go func() {
			for v := range ch {
				j := v.active - 1 - v.i
				arr[v.i] += arr[j]
				v.wg.Done()
			}
		}()
	}

	active := len(arr)
	for active > 1 {
		half := active / 2
		wg := sync.WaitGroup{}
		wg.Add(half)

		for i := range half {
			ch <- Task{arr: arr, active: active, i: i, wg: &wg}
		}

		wg.Wait()
		active = (active + 1) / 2
	}

	close(ch)

	return arr[0]
}
