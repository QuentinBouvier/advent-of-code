.PHONY: build clean

include .env

# Compiler and flags
CXX = g++
CXXFLAGS = -fdiagnostics-color=always -std=c++23 -g
INCLUDES = -Iday*
OUTPUT = bin/aoc

# Source files
SOURCES = main.cpp day*/*.cpp

# Default target
build: 
	@mkdir -p bin
	$(CXX) $(CXXFLAGS) $(SOURCES) $(INCLUDES) -o $(OUTPUT)

# Clean target
clean:
	rm -f $(OUTPUT)

# Run the compiled binary
run: build
	env AOC_DAY=${AOC_DAY} ./$(OUTPUT)
