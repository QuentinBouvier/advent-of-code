package y2023.day7

import kotlin.math.pow

abstract class AbstractP(private val lines: List<Pair<String, Int>>) {
    fun result(): Int {
        return lines
            .map { it to (handWeight(it.first) to handValueBase13(it.first)) }
            .sortedWith(compareBy({ it.second.first }, { it.second.second }))
            .mapIndexed { i, it -> it.first.second * (i + 1) }
            .sum()
    }

    protected abstract fun mostPresentChars2(str: String): Map<Char, Int>
    private fun isXofAKind(str: String, x: Int) = mostPresentChars2(str).any { it.value == x }
    private fun isFullHouse(str: String) = isXofAKind(str, 3) && isXofAKind(str, 2)
    private fun isTwoPair(str: String) = mostPresentChars2(str).all { it.value == 2 }
    private fun handWeight(str: String): Int = when {
        isXofAKind(str, 5) -> 7
        isXofAKind(str, 4) -> 6
        isFullHouse(str) -> 5
        isXofAKind(str, 3) -> 4
        isTwoPair(str) -> 3
        isXofAKind(str, 2) -> 2
        else -> 1
    }
    private fun handValueBase13(str: String) = str.map { cardToInt(it) }.mapIndexed { i, it -> 13.0.pow(5.0 - i) * it }.sum()
    protected abstract fun cardToInt(c: Char): Int
}