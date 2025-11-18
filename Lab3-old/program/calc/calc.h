#ifndef CALC_H
#define CALC_H

#include <cstdint>

class SumCalc {
public:
  virtual int64_t calcSum(int64_t *arr, int arr_size) = 0;
  virtual ~SumCalc() = default;
};

namespace normal {
class SingleSumCalc : public SumCalc {
public:
  int64_t calcSum(int64_t *arr, int arr_size) override;
};

class ParallelSumCalc : public SumCalc {
public:
  int64_t calcSum(int64_t *arr, int arr_size) override;
};
} // namespace normal

namespace wave {
class SingleSumCalc : public SumCalc {
public:
  int64_t calcSum(int64_t *arr, int arr_size) override;
};

class ParallelSumCalc : public SumCalc {
public:
  int64_t calcSum(int64_t *arr, int arr_size) override;
};
} // namespace wave
#endif
