package y2023.day7

fun main() {
    val example2 = """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483"""

    val example = """2345A 1
Q2KJJ 13
Q2Q2Q 19
T3T3J 17
T3Q33 11
2345J 3
J345A 2
32T3K 5
T55J5 29
KK677 7
KTJJT 34
QQQJA 31
JJJJJ 37
JAAAA 43
AAAAJ 59
AAAAA 61
2AAAA 23
2JJJJ 53
JJJJ2 41"""


    val trap = "JJJ89 1"

//    val input = example.split("\n").asSequence()
//    val input = example2.split("\n").asSequence()
//    val input = trap.split("\n").asSequence()
    val input = commons.inputsFileToLineSequence(2023, 7)


    val lines = input
        .map { it.split(" ") }
        .map { it[0] to it[1].toInt() }
        .toList()

//    val p1 = P1(lines)
    val p2 = P2(lines)

//    println("p1: ${p1.result()}")
    println("p2: ${p2.result()}")
}