package y2024.day2

import commons.inputsFileToLineSequence
import kotlin.math.abs

fun main() {
    val input = inputsFileToLineSequence(2024, 2).toList()

//    val input = """7 6 4 2 1
//1 2 7 8 9
//9 7 6 2 1
//1 3 2 4 5
//8 6 4 4 1
//1 3 6 7 9""".split("\n")

    part1(input)
    part2(input)
}

fun part1(input: List<String>) {
    println(input
        .map { line -> line.split(" ").map { it.toInt() } }
        .map { isSafe(it) }
        .count { it })
}

fun part2(input: List<String>) {
    println(input
        .map { line -> line.split(" ").map { it.toInt() } }
        .map { line ->
            if (isSafe(line)) {
                true
            } else {
                line.indices
                    .any { isSafe(line.toMutableList().apply { removeAt(it) }) }
            }
        }
        .count { it })
}

private fun isSafe(line: List<Int>): Boolean {
    val parsed = line.zipWithNext()
        .map { Pair(Direction.infer(it), abs(it.first - it.second)) }
    return (parsed.all { it.first == Direction.INCREASE } || parsed.all { it.first == Direction.DECREASE })
            && parsed.all { it.second in 1..3 }
}

enum class Direction {
    INCREASE, DECREASE;

    companion object {
        fun infer(input: Pair<Int, Int>) =
            if (input.first < input.second) INCREASE else DECREASE
    }
}