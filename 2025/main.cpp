#include <iostream>
#include <cstdlib>
#include <string>
#include "day1/day1.h"

void runDay(const int day) {
    if (day == 1) {
        day1();
    }
}

int main(int argc, char *argv[])
{
    char* day { std::getenv("AOC_DAY") };

    std::cout << "Running Advent of Code 2025 - Day " << std::stoi(day) << std::endl;

    runDay(static_cast<int>((day != nullptr) ? std::stoi(day) : 0));

    return 0;
}