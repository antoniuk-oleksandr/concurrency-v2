#ifndef TIMER_H
#define TIMER_H

#include "../calc/calc.h"
#include <chrono>
#include <iostream>
using namespace std;

class TimerDecorator : public SumCalc {
private:
  SumCalc *wrapped;

public:
  TimerDecorator(SumCalc *sumCalc) : wrapped(sumCalc) {}

  int64_t calcSum(int64_t *arr, int arr_size) override;
};

#endif
