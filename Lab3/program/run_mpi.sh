#!/bin/bash

# 1. Find all mpi_node containers in the Swarm service
HOSTS=$(getent hosts tasks.mpi_node | awk '{print $2}')

# 2. Convert host list to comma-separated string
HOSTLIST=$(echo $HOSTS | tr ' ' ',')

# 3. Count nodes for mpirun
NP=$(echo $HOSTS | wc -w)

echo "Detected MPI nodes: $HOSTS"
echo "Running with NP=$NP"
echo "Hostlist: $HOSTLIST"

# 4. Run MPI
mpirun --allow-run-as-root \
  -np $NP \
  -host $HOSTLIST \
  /app/program

