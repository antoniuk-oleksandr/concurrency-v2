#include "array/array.h"
#include "calc/calc.h"
#include "timer/timer.h"
#include <cstring>
#include <iostream>
#include <mpi.h>

int main(int argc, char **argv) {
  MPI_Init(&argc, &argv);

  int rank, size;
  MPI_Comm_rank(MPI_COMM_WORLD, &rank);
  MPI_Comm_size(MPI_COMM_WORLD, &size);

  // Read mode and threads (same on all nodes, but only rank 0 reads env).
  char modeBuf[64] = {0};

  if (rank == 0) {
    const char *modeVar = getenv("SUM_MODE");
    std::string mode = modeVar ? std::string(modeVar) : "normal_single";
    std::strncpy(modeBuf, mode.c_str(), 63);
  }

  // Broadcast mode to ALL processes
  MPI_Bcast(modeBuf, 64, MPI_CHAR, 0, MPI_COMM_WORLD);
  std::string mode(modeBuf);

  // Broadcast threads number
  int threads = 1;
  if (rank == 0) {
    const char *threadsVar = getenv("SUM_THREADS");
    threads = threadsVar ? std::stoi(threadsVar) : 1;
  }
  MPI_Bcast(&threads, 1, MPI_INT, 0, MPI_COMM_WORLD);

  // Array parameters
  int total_size = 100'000'000;
  int local_size = total_size / size;

  int64_t *full_arr = nullptr;
  int64_t *local_arr = new int64_t[local_size];

  if (rank == 0) {
    full_arr = new int64_t[total_size];
    genArr(full_arr, total_size, 100);
  }

  // Scatter array chunks
  MPI_Scatter(full_arr, local_size, MPI_LONG_LONG, local_arr, local_size,
              MPI_LONG_LONG, 0, MPI_COMM_WORLD);

  // Create calculation algorithm (the same on all ranks)
  SumCalcFactory factory(mode, threads);
  SumCalc *calc = factory.createSumCalc();
  calc = new TimerDecorator(calc);

  // Local sum
  int64_t local_sum = calc->calcSum(local_arr, local_size);

  // Gather global sum
  int64_t global_sum = 0;
  MPI_Reduce(&local_sum, &global_sum, 1, MPI_LONG_LONG, MPI_SUM, 0,
             MPI_COMM_WORLD);

  if (rank == 0) {
    std::cout << "GLOBAL RESULT: " << global_sum << std::endl;
    delete[] full_arr;
  }

  delete[] local_arr;
  delete calc;

  MPI_Finalize();
  return 0;
}
