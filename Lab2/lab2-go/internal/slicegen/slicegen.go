package slicegen

import "math/rand"

func Generate(size, max int) []int64 {
	arr := make([]int64, size )

	for i := range size {
		arr[i] = rand.Int63n(int64(max))
	}

	return arr
}
