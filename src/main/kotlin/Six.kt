package me.emilia

// 0,0 is top left
// -1,0 is left
// 0,1 is right
// x,y = column,row
data class Six(val map: String) {
    private val columns: Int = map.lines().first().length
    private val rows: Int = map.lines().filter { it.isNotEmpty() }.size
    private val startPosition: Pair<Int, Int>
    private val startDirection: Pair<Int, Int>
    private val obstacles: List<Pair<Int, Int>>

    init {
        val (startingLineIndex, startingLine) = map.lines().withIndex()
            .find { (_, it) -> it.any { it in charArrayOf('<', '>', '^', 'v') } }!!

        val startColumnIndex = startingLine.indexOfAny(charArrayOf('<', '>', 'v', '^'))
        startDirection = when (startingLine[startColumnIndex]) {
            '<' -> Pair(-1, 0)
            '>' -> Pair(1, 0)
            'v' -> Pair(0, 1)
            '^' -> Pair(0, -1)
            else -> {
                throw IllegalArgumentException()
            }
        }
        startPosition = Pair(startColumnIndex, startingLineIndex)

        obstacles = map.lines().withIndex()
            .flatMap { (index, line) ->
                line.withIndex().filter { it.value == '#' }
                    .map { Pair(it.index, index) }
            }

        obstacles.forEach { (x, y) ->
            require(map.lines()[y][x] == '#')
        }
    }

    fun calculate(): Int {
        val visited = mutableSetOf(startPosition)
        var currentPosition = startPosition
        var currentDirection = startDirection
        while (currentPosition.first in 0..<columns
            && currentPosition.second in 0..<rows
        ) {
            var nextPosition = currentPosition + currentDirection
            while (nextPosition in obstacles) {
                currentDirection = nextDirection(currentDirection)
                nextPosition = currentPosition + currentDirection
            }
            currentPosition = nextPosition
            visited.add(currentPosition)
        }

        return visited.size - 1
    }

    private fun nextDirection(current: Pair<Int, Int>) =
        when (current) {
            Pair(-1, 0) -> Pair(0, -1)
            Pair(0, -1) -> Pair(1, 0)
            Pair(1, 0) -> Pair(0, 1)
            Pair(0, 1) -> Pair(-1, 0)
            else -> throw IllegalArgumentException()
        }


}

// 0,0 is top left
// -1,0 is left
// 0,1 is right
// x,y = column,row
data class SixPartTwo(val map: String) {
    private val columns: Int = map.lines().first().length
    private val rows: Int = map.lines().filter { it.isNotEmpty() }.size
    private val startPosition: Pair<Int, Int>
    private val startDirection: Pair<Int, Int>
    private val obstacles: List<Pair<Int, Int>>

    init {
        val (startingLineIndex, startingLine) = map.lines().withIndex()
            .find { (_, it) -> it.any { it in charArrayOf('<', '>', '^', 'v') } }!!

        val startColumnIndex = startingLine.indexOfAny(charArrayOf('<', '>', 'v', '^'))
        startDirection = when (startingLine[startColumnIndex]) {
            '<' -> Pair(-1, 0)
            '>' -> Pair(1, 0)
            'v' -> Pair(0, 1)
            '^' -> Pair(0, -1)
            else -> {
                throw IllegalArgumentException()
            }
        }
        startPosition = Pair(startColumnIndex, startingLineIndex)

        obstacles = map.lines().withIndex()
            .flatMap { (index, line) ->
                line.withIndex().filter { it.value == '#' }
                    .map { Pair(it.index, index) }
            }

        obstacles.forEach { (x, y) ->
            require(map.lines()[y][x] == '#')
        }
    }

    fun calculate() =
        (0..<columns)
            .flatMap { column ->
                (0..<rows).map { row -> Pair(column, row) }
            }
            .parallelStream()
            .filter { it !in obstacles && it != startPosition && getsStuckInLoop(it) }
            .count()

    private fun getsStuckInLoop(extraObstacle: Pair<Int, Int>): Boolean {
        val previousStates = mutableSetOf(Pair(startPosition, startDirection))
        var currentPosition = startPosition
        var currentDirection = startDirection
        while (currentPosition.first in 0..<columns
            && currentPosition.second in 0..<rows
        ) {
            var nextPosition = currentPosition + currentDirection
            while (nextPosition in obstacles || nextPosition == extraObstacle) {
                currentDirection = nextDirection(currentDirection)
                nextPosition = currentPosition + currentDirection
            }
            currentPosition = nextPosition

            // we have been here before -> loop
            if (Pair(currentPosition, currentDirection) in previousStates) {
                println("With obstacle at $extraObstacle, found loop at $currentPosition, facing $currentDirection")
                return true
            }
            previousStates.add(Pair(currentPosition, currentDirection))
        }

        return false // left playing area without getting stuck in a loop
    }

    private fun nextDirection(current: Pair<Int, Int>) =
        when (current) {
            Pair(-1, 0) -> Pair(0, -1)
            Pair(0, -1) -> Pair(1, 0)
            Pair(1, 0) -> Pair(0, 1)
            Pair(0, 1) -> Pair(-1, 0)
            else -> throw IllegalArgumentException()
        }


}


private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) =
    Pair(this.first + other.first, this.second + other.second)
