#!/bin/bash

MODE=$1
THREADS=$2

echo
echo "Mode: $MODE"
echo "Threads: $THREADS"

# Find leader container (service replica 1)
LEADER=$(docker ps --format '{{.Names}} {{.ID}}' \
    | awk '/mpi_mpi_node\.1\./ {print $2; exit}')

if [[ -z "$LEADER" ]]; then
    echo "ERROR: Leader container not found!"
    exit 1
fi


echo "Leader container: $LEADER"

# Number of MPI nodes
NP=$(docker ps -q --filter "name=mpi_mpi_node" | wc -l)

echo "Nodes: $NP"

HOSTLIST="tasks.mpi_node"
echo "Using hostlist: $HOSTLIST"
echo

docker exec \
    -e SUM_MODE="$MODE" \
    -e SUM_THREADS="$THREADS" \
    -it "$LEADER" \
    mpirun --allow-run-as-root \
        -np "$NP" \
        -host "$HOSTLIST:$NP" \
        /app/program
