package y2023.day6

fun main() {
    val input = """Time:        35     69     68     87
Distance:   213   1168   1086   1248"""
    val example = """Time:      7  15   30
Distance:  9  40  200"""

    val lines = input.lines().toList()
    val times = lines[0].split(" ").drop(1).filter { it.isNotBlank() }.map { it.toLong() }
    val distances = lines[1].split(" ").drop(1).filter { it.isNotBlank() }.map { it.toLong() }

    val tuples = times.zip(distances)

    val p1 = tuples.map { calculateHolding(it) }
        .reduce { acc, i -> acc * i }

    println("p1: $p1")

    val timeP2 = lines[0].split(" ").drop(1).filter { it.isNotBlank() }.joinToString("").toLong()
    val distancesP2 = lines[1].split(" ").drop(1).filter { it.isNotBlank() }.joinToString("").toLong()

    val p2 = calculateHolding(timeP2 to distancesP2)
    println("p2: $p2")
}

private fun calculateHolding(tuple: Pair<Long, Long>) = (0..tuple.first)
    .map { it * (tuple.first - it) }
    .count { it > tuple.second }