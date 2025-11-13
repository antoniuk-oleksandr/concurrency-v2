cmake_minimum_required(VERSION 3.15)
project(Lab1 CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(OpenMP)
if(OpenMP_CXX_FOUND)
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${OpenMP_CXX_FLAGS}")
endif()

include_directories(
    ${PROJECT_SOURCE_DIR}/array
    ${PROJECT_SOURCE_DIR}/calc
    ${PROJECT_SOURCE_DIR}/timer
)

add_executable(main
    main.cpp
    array/array.cpp
    calc/normal.cpp
    calc/wave.cpp
    timer/timer.cpp
)
