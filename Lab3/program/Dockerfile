# Build stage
FROM ubuntu:22.04 AS builder

RUN apt-get update && \
    apt-get install -y build-essential cmake openmpi-bin libopenmpi-dev git

WORKDIR /app
COPY . .

RUN cmake -DCMAKE_CXX_COMPILER=mpicxx -B build -S . && \
    cmake --build build --parallel


# Runtime stage
FROM ubuntu:22.04

RUN apt-get update && \
    apt-get install -y openmpi-bin libopenmpi-dev openssh-server

WORKDIR /app

COPY --from=builder /app/build/main /app/program
COPY run_mpi.sh /app/run_mpi.sh

RUN chmod +x /app/run_mpi.sh

# Setup SSH
RUN mkdir /var/run/sshd && \
    ssh-keygen -A && \
    mkdir -p /root/.ssh && chmod 700 /root/.ssh && \
    ssh-keygen -t rsa -f /root/.ssh/id_rsa -N "" && \
    cat /root/.ssh/id_rsa.pub >> /root/.ssh/authorized_keys && \
    chmod 600 /root/.ssh/authorized_keys && \
    echo "Host *\n  StrictHostKeyChecking no\n" > /root/.ssh/config && \
    chmod 600 /root/.ssh/config

EXPOSE 22

CMD ["/usr/sbin/sshd", "-D"]
