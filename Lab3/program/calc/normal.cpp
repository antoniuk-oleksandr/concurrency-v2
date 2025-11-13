#include "calc.h"
#include <cstdint>
#include <omp.h>

namespace normal {
int64_t SingleSumCalc::calcSum(int64_t *arr, int arr_size) {
  int64_t sum = 0;

  for (int i = 0; i < arr_size; i++) {
    sum += arr[i];
  }

  return sum;
}

int64_t ParallelSumCalc::calcSum(int64_t *arr, int arr_size) {
  int64_t sum = 0;

#pragma omp parallel for reduction(+ : sum)
  for (int i = 0; i < arr_size; i++) {
    sum += arr[i];
  }

  return sum;
}
} // namespace normal
