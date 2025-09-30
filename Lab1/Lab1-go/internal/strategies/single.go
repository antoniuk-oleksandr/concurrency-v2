package strategies

import (
	sumManager "lab1-go/internal/manager"
)

type singleGouroutineSumStrategy struct {
	sumManager sumManager.SumManager
}

func NewSingleGouroutineSumStrategy(sumManager sumManager.SumManager) SumStrategy {
	return singleGouroutineSumStrategy{
		sumManager: sumManager,
	}
}

func (s singleGouroutineSumStrategy) CalcSum(arr []int) int64 {
	return s.sumManager.CalculateSum(arr, [2]int{0, len(arr)})
}
