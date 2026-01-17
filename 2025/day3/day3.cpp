#include <iostream>
#include <string>
#include "day3.h"

void day3::day3()
{
    std::ifstream input("day3/input.txt");
    std::ifstream example("day3/example.txt");

    if (!input.is_open())
    {
        std::cerr << "Failed to open input file." << std::endl;
        return;
    }

    day3::part1(input);
    input.clear();
    input.seekg(0, std::ios::beg);
    day3::part2(input);
}

void day3::part2(std::ifstream &input)
{
    std::cout << "Part 2:\n";
    std::string line;

    long result{0};

    while (std::getline(input, line))
    {
        char digits[13];
        char idx {0};
        for (int d{0}; d < 12; d++)
        {
            for (int i{idx}; i < line.size() - 11 + d; i++)
            {
                if (line.c_str()[i] > line.c_str()[idx])
                {
                    idx = i;
                }
            }
            digits[d] = line.c_str()[idx];
            ++idx;
        }

        digits[12] = '\0';
        // std::cout << "Found in line `" << line << "`: " << digits << '\n';
        result += std::stol(digits);
    }

    std::cout << "Result = " << result << '\n';
}

void day3::part1(std::ifstream &input)
{
    std::cout << "Part 1:\n";
    std::string line;

    long result{0};

    while (std::getline(input, line))
    {
        int first{0}, second;
        for (int i = 0; i < line.size() - 1; i++)
        {
            if (line.c_str()[i] > line.c_str()[first])
            {
                first = i;
            }
        }
        second = first + 1;
        for (int i = second; i < line.size(); i++)
        {
            if (line.c_str()[i] > line.c_str()[second])
            {
                second = i;
            }
        }

        std::string digits(2, ' ');
        digits.insert(0, 1, line.c_str()[first]);
        digits.insert(1, 1, line.c_str()[second]);

        result += stol(digits);

        // std::cout << "Found in line `" << line << "`: " << digits << '\n';
    }

    std::cout << "Result = " << result << '\n';
}