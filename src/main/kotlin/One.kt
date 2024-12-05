package me.emilia

import kotlin.math.abs

// https://adventofcode.com/2024/day/1

data class One(val leftSide: List<Int>, val rightSide: List<Int>) {
    init {
        require(leftSide.size == rightSide.size)
    }

    fun calculate() =
        leftSide.sorted().zip(rightSide.sorted())
            .sumOf { (left, right) -> abs(left - right) }

}

data class OnePartTwo(val leftSide: List<Int>, val rightSide: List<Int>) {
    init {
        require(leftSide.size == rightSide.size)
    }

    fun calculate(): Int {
        val occurrenceMap = rightSide.groupingBy { it }.eachCount()

        return leftSide.sumOf { left ->
            left * occurrenceMap.getOrDefault(left, 0)
        }
    }
}
