#include "array/array.h"
#include "calc/calc.h"
#include "timer/timer.h"
#include <cstdint>
#include <iostream>
using namespace std;

int main() {
  int arr_size = 100'000'000;
  int64_t *arr = new int64_t[arr_size];

  genArr(arr, arr_size, 100);

  SumCalc *calc = new normal::SingleSumCalc();
  calc = new TimerDecorator(calc);
  int64_t sum = calc->calcSum(arr, arr_size);
  cout << sum << endl;

  calc = new normal::ParallelSumCalc();
  calc = new TimerDecorator(calc);
  sum = calc->calcSum(arr, arr_size);
  cout << sum << endl;

  calc = new wave::SingleSumCalc();
  calc = new TimerDecorator(calc);
  sum = calc->calcSum(arr, arr_size);
  cout << sum << endl;

  calc = new wave::ParallelSumCalc();
  calc = new TimerDecorator(calc);
  sum = calc->calcSum(arr, arr_size);
  cout << sum << endl;

  delete[] arr;

  return 0;
}
