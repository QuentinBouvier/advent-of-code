#pragma once
#include <fstream>

namespace day2
{
    void part1(std::ifstream &input);
    void part2(std::ifstream &input);
    void day2();
    bool is_invalid(const long id);
    bool is_invalid_part_2(const long id);
}