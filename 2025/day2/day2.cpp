#include <iostream>
#include <string>
#include <vector>
#include <numeric>
#include "day2.h"

std::vector<std::string> split(const std::string& s, const std::string& delimiter) {
    size_t last = 0; 
    size_t next = 0;
    std::vector<std::string> tokens;
    while ((next = s.find(delimiter, last)) != std::string::npos) 
    {   
        tokens.push_back(s.substr(last, next-last));
        last = next + delimiter.size(); 
    } 
    tokens.push_back(s.substr(last));
    return tokens;
}

void day2::day2()
{
    std::ifstream input("day2/input.txt");
    std::ifstream example("day2/example.txt");

    if (!input.is_open())
    {
        std::cerr << "Failed to open input file." << std::endl;
        return;
    }

    day2::part1(input);
    input.clear();
    input.seekg(0, std::ios::beg);
    day2::part2(input);
}

void day2::part2(std::ifstream &input)
{
    std::cout << "Part 2:\n";
    std::string line;

    while (std::getline(input, line))
    {
        int delim_pos{0};
        int pos;
        long total {0};
        for (auto substr : split(line, ","))
        {
            delim_pos = pos + 1;

            int dash_pos = substr.find('-', 0);
            long range[2];
            range[0] = stol(substr.substr(0, dash_pos));
            range[1] = stol(substr.substr(dash_pos + 1));

            for (long i = range[0]; i <= range[1]; i++)
            {
                if (is_invalid_part_2(i))
                {
                    total += i;
                }
            }
        }
        std::cout <<  "Total: " << total << '\n';
    }
}

template <typename T>
bool all_equals(std::vector<T> vec)
{
    return std::adjacent_find( vec.begin(), vec.end(), std::not_equal_to<>() ) == vec.end();
}

bool day2::is_invalid_part_2(const long id)
{
    const std::string val{std::to_string(id)};
    const int id_length = val.size();
    for (int d = 2; d <= id_length; d++)
    {
        if (id_length % d == 0) {
            const int chunk_size = id_length/d;
            std::vector<std::string> parts(d);
            for (int i = 0; i < d; i++)
            {
                parts[i] = val.substr(i*chunk_size, chunk_size);
            }
            if (all_equals(parts))
            {
                return true;
            }
        }
    }

    return false;
}

void day2::part1(std::ifstream &input)
{
    std::cout << "Part 1:\n";
    std::string line;

    while (std::getline(input, line))
    {
        int delim_pos{0};
        int pos;
        long total {0};
        while ((pos = line.find(',', delim_pos + 1)) != -1)
        {
            std::string substr = line.substr(delim_pos, pos - delim_pos);
            delim_pos = pos + 1;

            int dash_pos = substr.find('-', 0);
            long range[2];
            range[0] = stol(substr.substr(0, dash_pos));
            range[1] = stol(substr.substr(dash_pos + 1));

            for (long i = range[0]; i <= range[1]; i++)
            {
                if (is_invalid(i))
                {
                    total += i;
                }
            }
        }
        std::cout <<  "Total: " << total << '\n';
    }
}

bool day2::is_invalid(const long id)
{
    const std::string val{std::to_string(id)};
    const int id_length = val.size();
    if (id_length % 2 != 0)
    {
        return false;
    }

    std::string parts[2] { val.substr(0,id_length/2), val.substr(id_length/2) };
    if (parts[0] == parts[1])
    {
        return true;
    }
    return false;
}