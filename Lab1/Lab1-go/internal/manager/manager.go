package summanager

type SumManager interface {
	CalculateSum(arr []int, boundaries [2]int) int64
	CalcSubArrBoundaries(arr []int, numOfGoroutines, index int) [2]int
}

type sumManager struct{}

func New() SumManager {
	return &sumManager{}
}

func (s *sumManager) CalculateSum(arr []int, boundaries [2]int) int64 {
	var sum int64 = 0
	for i := boundaries[0]; i < boundaries[1]; i++ {
		sum += int64(arr[i])
	}

	return sum
}

func (s *sumManager) CalcSubArrBoundaries(arr []int, numOfGoroutines, index int) [2]int {
	chunkSize := len(arr) / numOfGoroutines
	start := index * chunkSize
	end := start + chunkSize

	return [2]int{start, end}
}
