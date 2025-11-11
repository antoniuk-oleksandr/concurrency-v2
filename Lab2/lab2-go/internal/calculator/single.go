package calculator

type single struct{}

func NewSingleCalculator() Calculator {
	return &single{}
}

func (s *single) CalcSum(arr []int64) int64 {
	active := len(arr)
	for active > 1 {
		half := active / 2
		for i := range half {
			j := active - 1 - i
			arr[i] += arr[j]
		}
		active = (active + 1) / 2
	}

	return arr[0]
}
