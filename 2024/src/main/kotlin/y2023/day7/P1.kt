package y2023.day7

class P1(lines: List<Pair<String, Int>>) : AbstractP(lines) {

    override fun mostPresentChars2(str: String) = str.toCharArray().groupBy { it }.map { it.key to it.value.size }.sortedByDescending { it.second }.take(2).toMap()
    override fun cardToInt(c: Char): Int = when (c) {
        in '2'..'9' -> c - '0' - 2
        'T' -> 8
        'J' -> 9
        'Q' -> 10
        'K' -> 11
        'A' -> 12
        else -> error("invalid card")
    }
}