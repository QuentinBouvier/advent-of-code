package y2023.day5

import commons.inputsFileToLineSequence

fun main() {
/*    val ls = """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4""".split('\n').asSequence()*/
    val ls = inputsFileToLineSequence(2023, 5)

    // prep
    val lsIter = ls.iterator()
    val seeds = lsIter.next()
        .split(':')[1]
        .trim()
        .split(' ')
        .map { it.toLong() }
    val matrix: MutableList<MutableList<SeedMap>> = mutableListOf()

    while (lsIter.hasNext()) {
        val l = lsIter.next()
        if (l.isBlank()) continue
        if (l.none { it.isDigit() }) {
            matrix.add(mutableListOf())
            continue
        }

        val m = l.trim().split(' ').map { it.toLong() }
        matrix.last().add(SeedMap(m[0], m[1], m[2]))
    }

    // p1
    val p1 = seeds.minOfOrNull { seed ->
        matrix.fold(seed) { dest, maps ->
            maps.find { it.source.contains(dest) }
                ?.let { it.dest.first + (dest - it.source.first) }
                ?: dest
        }
    }

    println("p1: $p1")

    // p2

    val p2 = seeds
        .chunked(2) { it[0]..<it[0] + it[1] }
        .minOf { ranges ->
            ranges.minOf { seed ->
                matrix.fold(seed) { dest, maps ->
                    maps.find { it.source.contains(dest) }
                        ?.let { it.dest.first.plus((dest - it.source.first)) }
                        ?: dest
                }
            }
        }
    println("p2: $p2")
}

class SeedMap(
    val dest: LongRange,
    val source: LongRange,
) {
    constructor(destStart: Long, sourceStart: Long, range: Long) : this(
        destStart..<destStart + range,
        sourceStart..<sourceStart + range
    )

    override fun toString(): String {
        return "$dest $source"
    }
}