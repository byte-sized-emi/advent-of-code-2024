package me.emilia

enum class Direction {
    Up, Down, Left, Right;
    fun toCoord(): Coord = when (this) {
        Up -> Coord(0, -1)
        Down -> Coord(0, 1)
        Left -> Coord(-1, 0)
        Right -> Coord(1, 0)
    }
}

data class Ten(val map: List<List<Int>>) {
    val columns: Int = map.first().size
    val rows: Int = map.size

    fun calculate(): Int {
        val trailHeads = getTrailHeads()

        return trailHeads.sumOf { trailHeadScore(it) }
    }

    fun calculatePartTwo(): Int {
        val trailHeads = getTrailHeads()

        return trailHeads.sumOf { trailHeadScorePartTwo(it) }
    }

    private fun getTrailHeads(): List<Coord> =
        map.flatMapIndexed { lineIndex, line ->
            line.withIndex().filter { (_, height) -> height == 0}
                .map {(colIndex, _) -> Coord(colIndex, lineIndex) }
    }

    private fun trailHeadScore(coord: Coord): Int {
        var currentOptions = setOf(coord)
        for (currentHeight in 0..<9) {
            currentOptions = currentOptions.flatMap { tile ->
                Direction.entries.map { dir -> dir.toCoord() }
                    .map { dir -> tile + dir }
                    .filter { coord -> coord.x in 0..<columns && coord.y in 0..<rows }
                    .filter { coord ->
                        map[coord.y][coord.x] == currentHeight + 1
                    }
            }.toSet()
        }
        return currentOptions.size
    }

    private fun trailHeadScorePartTwo(coord: Coord): Int {
        var currentOptions = listOf(coord)
        for (currentHeight in 0..<9) {
            currentOptions = currentOptions.flatMap { tile ->
                Direction.entries.map { dir -> dir.toCoord() }
                    .map { dir -> tile + dir }
                    .filter { coord -> coord.x in 0..<columns && coord.y in 0..<rows }
                    .filter { coord ->
                        map[coord.y][coord.x] == currentHeight + 1
                    }
            }
        }
        return currentOptions.size
    }
}
