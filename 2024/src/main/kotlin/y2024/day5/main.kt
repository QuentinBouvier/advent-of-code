package y2024.day5

import commons.inputsFileToLineSequence
import kotlin.math.floor

fun main() {
    val input = inputsFileToLineSequence(2024, 5).toList()

    val example = """47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47""".split("\n")

    println("p1:${part1(input)}")
    println("p2:${part2(input)}")
}

fun part1(input: List<String>): Int {
    return sortAndFilter(input)
        .first
        .sumOf { it.second[(it.second.size/2)] }
}

fun sortAndFilter(input: List<String>): Pair<List<Pair<List<Int>, List<Int>>>, List<Pair<List<Int>, List<Int>>>> {
    val (order, pages) = input.filterNot { it == "" }.partition { it.contains('|') }
    val orderIntPairs = order
        .map {
            val (a, b) = it.split("|").map { it.toInt() }
            Pair(a, b)
        }
    val comparator = Comparator<Int> { a, b -> if (orderIntPairs.filter { it.first == a }.any { it.second == b}) -1 else 0 }

    return pages.map { it.split(",").map { it.toInt() } }
        .map { Pair(it, it.sortedWith(comparator)) }
        .partition { it.first == it.second }
}

fun part2(input: List<String>): Int {
    return sortAndFilter(input)
        .second
        .sumOf { it.second[(it.second.size/2)] }
}
