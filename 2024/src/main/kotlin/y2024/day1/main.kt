package y2024.day1

import commons.inputsFileToLineSequence
import kotlin.math.abs

fun main() {
    val input = inputsFileToLineSequence(2024, 1).toList()

//    val input = """3   4
//4   3
//2   5
//1   3
//3   9
//3   3""".split("\n")

    part1(input)
    part2(input)
}

fun part1(input: List<String>) {
    val unzipped = input.map {
        val split = it.split("   ")
        Pair(split[0].toInt(), split[1].toInt())
    }.unzip()
    val l1 = unzipped.first.sorted()
    val l2 = unzipped.second.sorted()
    val result = l1.zip(l2).sumOf { abs(it.first - it.second) }
    println(result)
}

fun part2(input: List<String>) {
    val unzipped = input.map {
        val split = it.split("   ")
        Pair(split[0].toInt(), split[1].toInt())
    }.unzip()
    val result = unzipped.first.sumOf() { i ->
        val o = unzipped.second.count { it == i }
        (i * o)
    }
    println(result)
}