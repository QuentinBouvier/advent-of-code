package y2023.day2

import java.io.File
import java.nio.file.Paths

fun main() {
    val fileBuffer = File("${Paths.get("").toAbsolutePath()}/src/main/kotlin/day2/input.txt").bufferedReader()

    val limits = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    var p1 = 0
    var p2 = 0
    fileBuffer.lineSequence()
        .map { l ->
            Pair(l.substringBefore(':').filter { it.isDigit() }.toInt(),
                l.substringAfter(':')
                    .split(';')
                    .flatMap { it.split(',') }
                    .map { it.partition { s -> s.isDigit() } }
                    .groupBy { it.second }
                    .map { Pair(it.key.trim(), it.value.maxOf { v -> v.first.trim().toInt() }) }
                    .toMap())
        }
        .forEach { g ->
            p1 += if (g.second.none { limits[it.key]!! < it.value }) g.first else 0
            p2 += g.second.values.reduce { acc, i -> acc * i }
        }

    println("p1: $p1")
    println("p2: $p2")
}