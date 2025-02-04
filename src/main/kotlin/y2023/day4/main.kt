package y2023.day4

import commons.inputsFileToLineSequence
import kotlin.math.pow

fun main() {
    val ls = inputsFileToLineSequence(2023,4)

//    val ls = """Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
//Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
//Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
//Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
//Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
//Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"""
//        .split("\n")
//        .asSequence()

    val lines = ls
        .toList()
        .map { line ->
            val splitCardId = line.split(':')
            val cardNo = splitCardId[0].filter { it.isDigit() }.toInt()
            val splitCardValues = splitCardId[1].split('|')
            val cardNumbers = splitCardValues[0].split(Regex("\\s")).filter { it.isNotBlank() }.map { it.toInt() }
            val winningNumbers = splitCardValues[1].split(Regex("\\s")).filter { it.isNotBlank() }.map { it.toInt() }
            Pair(cardNo, winningNumbers.filter { cardNumbers.contains(it) }.size)
        }
    val linesWithValue = lines.filter { it.second > 0 }

    val p1 = linesWithValue.sumOf { 2.0.pow(it.second - 1).toInt() }
    println("p1: $p1")

    val cardsValues = linesWithValue.associate { Pair(it.first, it.first + 1..it.first + it.second) }
    val cardsBag = (1..lines.last().first).associateWithTo(mutableMapOf()) { 1 }
    cardsValues.forEach { v -> val factor = cardsBag[v.key]; v.value.forEach { cardsBag[it] = cardsBag[it]!! + factor!! } }
    println("p2: ${cardsBag.values.sum()}")
}
