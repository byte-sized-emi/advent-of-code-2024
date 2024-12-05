package me.emilia

class Three(input: String) {
    val calculations: List<Pair<Int, Int>>
    init {
        calculations = input.windowed("mul(111,111)".length, partialWindows = true)
            .asSequence()
            .map { tryParseString(it) }
            .filter { it != null }
            .map { it!! }
            .toList()
    }

    private fun tryParseString(input: String): Pair<Int, Int>? {
        var s = input
        if (s.length > "mul(111,111)".length || s.length < "mul(1,1)".length) return null

        if (!s.startsWith("mul(")) return null

        s = s.substring(4)

        val numDigits = numDigitsAtStart(s)
        if (numDigits !in 1..3) return null

        val first = s.substring(0, numDigits).toInt()

        s = s.substring(numDigits)

        if (!s.startsWith(',')) return null

        s = s.substring(1)

        val numSecondDigits = numDigitsAtStart(s)
        if (numSecondDigits !in 1..3) return null

        val second = s.substring(0, numSecondDigits).toInt()
        s = s.substring(numSecondDigits)

        if (!s.startsWith(')')) return null

        return Pair(first, second)
    }

    private fun numDigitsAtStart(it: String): Int  {
        var count = 0
        for (digit in it) {
            if (digit.isDigit()) {
                count++
            } else {
                break
            }
        }

        return count
    }

    fun calculate(): Int =
        calculations.sumOf { (first, second) -> first * second}
}