package y2023.day3

import java.io.File
import java.nio.file.Paths
import kotlin.math.max
import kotlin.math.min

fun main() {
    val fileBuffer = File("${Paths.get("").toAbsolutePath()}/src/main/kotlin/day3/input.txt").bufferedReader()

    var lineBefore: String
    var line = ""
    var lineAfter: String? = fileBuffer.readLine()

    val engineParts: MutableList<Int> = ArrayList()
    val gears: MutableList<Int> = ArrayList()

    var i = 0

    do {
        i++
        lineBefore = line
        line = lineAfter!!
        lineAfter = fileBuffer.readLine()
        val splitLine = splitLineNumbers(line)
        val splitBefore = splitLineNumbers(lineBefore)
        val splitAfter = splitLineNumbers(lineAfter ?: "")
        val stars = starIndexes(line)

        engineParts.addAll(splitLine.filter {
            val start = it.key - 1
            val end = it.key + it.value.toString().length + 1
            lineMatchSymbol(lineAfter, start, end)
                    || lineMatchSymbol(line, start, end)
                    || lineMatchSymbol(lineBefore, start, end)
        }.values)
        stars.forEach {
            var matches = gearMatches(splitBefore, it) + gearMatches(splitLine, it) + gearMatches(splitAfter, it)
            if (matches.size == 2) {
                gears.add(matches.reduce { t, i -> t * i })
            }
        }
    } while (lineAfter != null)

    println("p1: ${engineParts.sum()}")
    println("P2: ${gears.sum()}")
}

private fun gearMatches(splitBefore: Map<Int, Int>, l: Int) =
    splitBefore.filter { (it.key - 1..it.key + it.value.toString().length).contains(l) }.values

private fun splitLineNumbers(l1: String): Map<Int, Int> {
    var holder = Pair(-1, "")
    val splitLine: MutableList<Pair<Int, String>> = ArrayList()
    l1.forEachIndexed { i, c ->
        if (c.isDigit()) {
            holder = if (holder.first == -1) {
                Pair(i, "${holder.second}$c")
            } else {
                Pair(holder.first, "${holder.second}$c")
            }
        } else {
            if (holder.first > -1) {
                splitLine.add(holder.copy())
                holder = Pair(-1, "")
            }
        }
    }
    if (holder.first > 0) {
        splitLine.add(holder)
    }
    return splitLine.associate { Pair(it.first, it.second.toInt()) }
}

private fun starIndexes(line: String): List<Int> =
    line.mapIndexed { i, c -> if (c == '*') i else -1 }.filter { it >= 0 }

private fun lineMatchSymbol(line: String?, start: Int, end: Int): Boolean =
    !line.isNullOrBlank()
            && line.subSequence(max(start, 0), min(end, line.length)).contains(Regex("[^.\\d]"))
