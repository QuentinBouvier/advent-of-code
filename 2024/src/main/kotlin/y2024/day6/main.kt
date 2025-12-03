package y2024.day6

import commons.inputsFileToLineSequence

fun main() {
    val input = inputsFileToLineSequence(2024, 6).toList()

    val example = """....#.....
.........#
..........
..#.......
.......#..
..........
.#..^.....
........#.
#.........
......#...""".split("\n")

    println("p1:${part1(input)}")
//    println("p2:${part2(input)}")
}

fun part1(input: List<String>): Int {
    var pos = input
        .mapIndexed { i, it -> Pair(i, it.indexOf("^")) }
        .first { it.second != -1 }

    val transforms = arrayOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    val nav = input.toMutableList()
    var step = 0

    while (true) {
        val dir = transforms[step % 4]
        val next = Pair(pos.first + dir.first, pos.second + dir.second)
        if (next.first < 0 || next.first >= input.size || next.second < 0 || next.second >= input[0].length) {
            break
        }
        if (nav[next.first][next.second] == '#') {
            step++
        } else {
            pos = next
            nav[pos.first] = nav[pos.first].replaceRange(pos.second, pos.second + 1, "^")
        }
    }

    return nav.joinToString().count { it == '^' }
}

fun part2(input: List<String>): Int {
    TODO("Not yet implemented")
}
