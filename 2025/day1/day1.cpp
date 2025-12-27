#include <iostream>
#include <fstream>
#include <sstream>

void part1(std::ifstream &input);
void part2(std::ifstream &input);

void day1()
{
    std::ifstream input("day1/input.txt");
    std::ifstream example("day1/example.txt");

    if (!input.is_open())
    {
        std::cerr << "Failed to open input file." << std::endl;
        return;
    }

    part1(input);
    part2(input);
}

void part2(std::ifstream &input)
{
    std::cout << "Part 2:\n";
    std::string line;
    int dial = 50;
    int zeros = 0;

    while (std::getline(input, line))
    {
        char sign = line[0];
        int rotation = stoi(line.substr(1));

        // Bruteforce (booh :( )
        for (int i = 0; i < rotation; i++)
        {
            if (line[0] == 'L')
            {
                dial -= 1;
            }
            else
            {
                dial += 1;
            }

            if ((dial % 100) == 0)
            {
                zeros++;
            }
        }
    }

    std::cout << "Number of times rotation is zero: " << zeros << "\n\n";
}

void part1(std::ifstream &input)
{
    std::cout << "Part 1:\n";
    std::string line;
    int dial = 50;
    int zeros = 0;

    while (std::getline(input, line))
    {
        char sign = line[0];
        int rotation = stoi(line.substr(1));
        if (sign == 'R')
        {
            dial += rotation % 100;
        }
        else if (sign == 'L')
        {
            dial -= rotation % 100;
        }

        if (dial >= 100)
        {
            dial -= 100;
        }
        if (dial < 0)
        {
            dial += 100;
        }

        if (dial == 0)
        {
            zeros++;
        }
    }

    std::cout << "Number of times rotation is zero: " << zeros << "\n\n";
}