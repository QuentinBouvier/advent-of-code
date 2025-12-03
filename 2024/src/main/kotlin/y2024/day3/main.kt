package y2024.day3

import commons.inputsFileToLineSequence

fun main() {
    val input = inputsFileToLineSequence(2024, 3).joinToString()

//    val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

    println("p1:${part1(input)}")
    println("p1:${part2(input)}")
}

fun part1(input: String): Int {
    val pattern = "mul\\(\\d+,\\d+\\)"
    val regex = Regex(pattern)
    return regex.findAll(input)
        .sumOf { applyMul(it.value) }
}

fun part2(input: String): Int {
    return "do()$input".split("do()")
        .map { it.split("don't()")[0] }
        .sumOf { part1(it) }

}

fun applyMul(input: String): Int {
    val first = input.split("(")[1].split(",")[0].toInt()
    val second = input.split(",")[1].trimEnd(')').toInt()
    return first * second
}
