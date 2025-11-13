#include "timer.h"
using namespace chrono;

int64_t TimerDecorator::calcSum(int64_t *arr, int arr_size) {
  auto start = high_resolution_clock::now();

  int64_t result = wrapped->calcSum(arr, arr_size);

  auto end = high_resolution_clock::now();
  auto duration = duration_cast<milliseconds>(end - start).count();

  cout << "Time: " << duration << " ms" << endl;

  return result;
}
