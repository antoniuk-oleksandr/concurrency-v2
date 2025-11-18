#include "calc.h"
#include <stdexcept>

SumCalc *SumCalcFactory::createSumCalc() {
  if (mode == "normal_single") {
    return new normal::SingleSumCalc();
  } else if (mode == "normal_parallel") {
    return new normal::ParallelSumCalc(threads);
  } else if (mode == "wave_single") {
    return new wave::SingleSumCalc();
  } else if (mode == "wave_parallel") {
    return new wave::ParallelSumCalc(threads);
  }

  throw std::invalid_argument("Unknown SUM_MODE: " + mode);
}
