package y2023.day1

import java.io.File
import java.nio.file.Paths

fun main() {
    val fileBufferp1 = File("${Paths.get("").toAbsolutePath()}/src/main/kotlin/day1/input.txt").bufferedReader()
    val fileBufferp2 = File("${Paths.get("").toAbsolutePath()}/src/main/kotlin/day1/input.txt").bufferedReader()

    val totalp1 = fileBufferp1.lineSequence()
        .map { l -> l.filter { it.isDigit() } }
        .map { "${it.first()}${it.last()}".toInt() }
        .sum()

    val totalp2 = fileBufferp2.lineSequence()
        .map { numbersToDigit(it) }
        .map { l -> l.filter { it.isDigit() } }
        .map { "${it.first()}${it.last()}".toInt() }
        .sum()

    println(totalp1)
    println(totalp2)
}

fun numbersToDigit(l: String): String {
    val numbers = mapOf(
        "one" to "o1e",
        "two" to "t2o",
        "three" to "t3e",
        "four" to "f4r",
        "five" to "f5e",
        "six" to "s6x",
        "seven" to "s7n",
        "eight" to "e8t",
        "nine" to "n9e"
    )
    return numbers
        .entries
        .fold(l) { acc, n -> acc.replace(n.key, n.value) }
}