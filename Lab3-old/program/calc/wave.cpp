#include "calc.h"

namespace wave {
int64_t SingleSumCalc::calcSum(int64_t *arr, int arr_size) {
  int active = arr_size;

  while (active > 1) {
    int half = active / 2;

    for (int i = 0; i < half; i++) {
      int j = active - 1 - i;
      arr[i] += arr[j];
    }

    active = (active + 1) / 2;
  }

  return arr[0];
}

int64_t ParallelSumCalc::calcSum(int64_t *arr, int arr_size) {
  int active = arr_size;

  while (active > 1) {
    int half = active / 2;

#pragma omp parallel for
    for (int i = 0; i < half; i++) {
      int j = active - 1 - i;
      arr[i] += arr[j];
    }

    active = (active + 1) / 2;
  }

  return arr[0];
}
} // namespace wave
