#include "array.h"
#include <cstdint>
#include <random>
using namespace std;

int randomInt(int min, int max) {
  static random_device rd;
  static mt19937 gen(rd());
  uniform_int_distribution<> dist(min, max);
  return dist(gen);
}

void genArr(int64_t *arr, uint32_t len, uint32_t max) {
  for (int i = 0; i < len; i++) {
    arr[i] = randomInt(0, max);
  }
}
