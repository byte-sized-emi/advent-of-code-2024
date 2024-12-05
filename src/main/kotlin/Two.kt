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

    fun calculate(): Int =
        input.count { report ->
            if (reportIsGood(report)) {
                return@count true
            } else {
                for (i in report.indices) {
                    val fixedReport = report
                        .filterIndexed { idx, _ -> idx != i }
                        .toList()
                    if (reportIsGood(fixedReport)) {
                        return@count true
                    }
                }
                return@count false
            }
        }


    private fun reportIsGood(report: List<Int>): Boolean {
        // check if only increasing/decreasing
        val increasing = report.first() < report[1]
        val isSteady = report
            .windowed(2)
            .all { (prev, next) ->
                if (increasing) {
                    prev < next
                } else {
                    prev >= next
                }
            }

        if (!isSteady) {
            return false;
        }

        // check for increase/decrease between 1..3
        return report
            .windowed(2)
            .all { (prev, next) ->
                abs(prev - next) in 1..3
            }
    }
}