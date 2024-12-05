package me.emilia

import kotlin.math.abs

data class Two(val input: List<List<Int>>) {
    init {
        require(input.isNotEmpty())
        input.forEach {
            require(it.isNotEmpty())
        }
    }
    // Return how many reports match the criteria
    fun calculate(): Int =
        input.count { report ->
            // check if only increasing/decreasing
            val increasing = report.first() < report[1]
            val isSteady = report.windowed(2)
                .all { (prev, next) ->
                    if (increasing) {
                        prev < next
                    } else {
                        prev >= next
                    }
                }

            if (!isSteady) {
                return@count false;
            }

            // check for increase/decrease between 1..3
            report.windowed(2)
                .all { (prev, next) ->
                    abs(prev - next) in 1..3
                }
        }
}

data class TwoPartTwo(val input: List<List<Int>>) {
    init {
        require(input.isNotEmpty())
        input.forEach {
            require(it.isNotEmpty())
        }
    }
    // Return how many reports match the criteria
    fun calculate(): Int =
        input.count { report ->
            // check if only increasing/decreasing
            val increasing = report.first() < report[1]
            // number of good ("steady") pairs
            val steadyCount = report.windowed(2)
                .count { (prev, next) ->
                    if (increasing) {
                        prev < next
                    } else {
                        prev >= next
                    }
                }

            // total number of pairs - steadyCount
            val unsteadyCount = report.size + 1 - steadyCount
            if (!(unsteadyCount == 0 || unsteadyCount == 2)) {
                return@count false;
            }

            // check for increase/decrease between 1..3
            val correctCount = report.windowed(2)
                .count { (prev, next) ->
                    abs(prev - next) in 1..3
                }
            val notCorrectCount = report.size + 1 - correctCount
            return@count notCorrectCount == 0 || notCorrectCount == 2
        }
}