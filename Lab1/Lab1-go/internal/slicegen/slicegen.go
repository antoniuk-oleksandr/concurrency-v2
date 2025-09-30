package slicegen

import "math/rand"

func Generate(size, max int) []int {
	arr := make([]int, size )

	for i := range size {
		arr[i] = rand.Intn(max)
	}

	return arr
}
