package y2023.day7

class P2(lines: List<Pair<String, Int>>) : AbstractP(lines) {
    override fun mostPresentChars2(str: String): Map<Char, Int> {
        if (str == "JJJJJ") return mapOf('J' to 5)
        val cardBag = str
            .toCharArray()
            .groupBy { it }
            .map { it.key to it.value.size }
            .sortedByDescending { it.second }

        val jValue = cardBag.find { it.first == 'J' }
        if(jValue != null) {
            val bagWithoutJ = cardBag.filter { it.first != 'J' }
            val addedJ = bagWithoutJ
                .first()
                .let { it.first to it.second + jValue.second }

            return (listOf(addedJ) + bagWithoutJ.drop(1)).toMap()
        }
        return cardBag.toMap()
    }

    override fun cardToInt(c: Char): Int = when (c) {
        in '2'..'9' -> c - '0' - 1
        'T' -> 9
        'J' -> 0
        'Q' -> 10
        'K' -> 11
        'A' -> 12
        else -> error("invalid card")
    }
}