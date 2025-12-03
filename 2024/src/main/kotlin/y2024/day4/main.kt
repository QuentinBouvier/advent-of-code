package y2024.day4

import commons.inputsFileToLineSequence

fun main() {
    val input = inputsFileToLineSequence(2024, 4).toList()

//    val input = """MMMSXXMASM
//MSAMXMSMSA
//AMXSXMAAMM
//MSAMASMSMX
//XMASAMXAMM
//XXAMMXXAMA
//SMSMSASXSS
//SAXAMASAAA
//MAMMMXMMMM
//MXMXAXMASX""".split("\n")

    println("p1:${part1(input)}")
    println("p2:${part2(input)}")
}

fun part1(input: List<String>): Int {
    var count = 0

    count += countXmas(input)
    val cols = getCols(input)
    count += countXmas(cols)
    val diag1 = rotate45clockwise(input, 4)
    count += countXmas(diag1)
    val diag2 = rotate45clockwise(input.map { it.reversed()} , 4)
    count += countXmas(diag2)

    return count
}

fun getCols(matrix: List<String>): List<String> {
    return matrix.indices
        .map { i -> String(matrix.map { it[i] }.toCharArray()) }
}

fun rotate45clockwise(matrix: List<String>, minLength: Int): List<String> {
    val result = mutableListOf<MutableList<Char>>()
    var l = 0
    var i = 0
    for (j in 0..<matrix[0].length - i) {
        result.add(mutableListOf())
        for (k in 0..j) {
            val y = i + k
            val x = j - k
            result[l].add(matrix[x][y])
        }
        l++
    }

    i = 1
    for (j in 0..<matrix[0].length - i) {
        result.add(mutableListOf())
        for (k in 0..<matrix[0].length - i - j) {
            val y = i + k + j
            val x = matrix.size - i - k
            result[l].add(matrix[x][y])
        }
        l++
    }

    return result
        .filter { it.size >= minLength }
        .map { String(it.toCharArray()) }
}

fun countXmas(sequences: List<String>): Int {
    return sequences.sumOf { l -> l.windowed(4) { if (it.equals("XMAS") || it.equals("SAMX")) 1 else 0 }.sum() }
}

fun part2(input: List<String>): Int {
    val count = (0..input.size - 3)
        .sumOf { i ->
            (0..input[i].length - 3)
                .count { j ->
                    val matrix = get3x3Matrix(input, i, j)
                    isXMAS(matrix)
                }
        }

    return count
}

fun get3x3Matrix(input: List<String>, x: Int, y: Int): List<List<Char>> {
    return input.windowed(3)[x].map { it.windowed(3)[y].toCharArray().toList() }
}

fun isXMAS(matrix: List<List<Char>>): Boolean {
    /*
    matches
    M.S      S.S      S.M      M.M
    .A.  or  .A.  or  .A.  or  .A.
    M.S      M.M      S.M      S.S
     */
    return  matrix[1][1] == 'A'
            && ((matrix[0][0] == 'M' && matrix[0][2] == 'S' && matrix[2][0] == 'M' && matrix[2][2] == 'S')
            || (matrix[0][0] == 'S' && matrix[0][2] == 'S' && matrix[2][0] == 'M' && matrix[2][2] == 'M')
            || (matrix[0][0] == 'S' && matrix[0][2] == 'M' && matrix[2][0] == 'S' && matrix[2][2] == 'M')
            || (matrix[0][0] == 'M' && matrix[0][2] == 'M' && matrix[2][0] == 'S' && matrix[2][2] == 'S'))
}