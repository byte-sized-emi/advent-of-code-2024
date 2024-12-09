package me.emilia

// x,y
// column,row
data class Coord(val x: Int, val y: Int) {
    operator fun plus(other: Coord) = Coord(x + other.x, y + other.y)
    operator fun minus(other: Coord) = Coord(x - other.x, y - other.y)
    operator fun unaryMinus() = Coord(-x, -y)
}

data class Antenna(val freq: Char, val coord: Coord)

data class Eight(val antennas: List<Antenna>, val columns: Int, val rows: Int) {
    private val frequencies: Set<Char> = antennas
        .map { it.freq }
        .toSet()

    fun calculate(): Int =
        frequencies
            .flatMap { freq ->
                val freqAntennas = antennas.filter { it.freq == freq }
                calculateAntiNodes(freqAntennas)
            }
            .filter { it.x in 0..<columns && it.y in 0..<rows}
            .toSet()
            .size

    private fun calculateAntiNodes(freqAntennas: List<Antenna>): Set<Coord> {
        val antiNodes = mutableSetOf<Coord>()
        for (first in freqAntennas) {
            for (second in freqAntennas) {
                if (first == second) continue

                val diff = second.coord - first.coord // vector from first to second
                antiNodes.add(second.coord + diff)
                antiNodes.add(first.coord - diff)

            }
        }
        return antiNodes
    }
}

data class EightPartTwo(val antennas: List<Antenna>, val columns: Int, val rows: Int) {
    private val frequencies: Set<Char> = antennas
        .map { it.freq }
        .toSet()

    fun calculate(): Int =
        frequencies
            .flatMap { freq ->
                val freqAntennas = antennas.filter { it.freq == freq }
                calculateAntiNodes(freqAntennas)
            }
            .filter { it.x in 0..<columns && it.y in 0..<rows}
            .toSet()
            .size

    private fun calculateAntiNodes(freqAntennas: List<Antenna>): Set<Coord> {
        val antiNodes = mutableSetOf<Coord>()
        for (first in freqAntennas) {
            for (second in freqAntennas) {
                if (first == second) continue

                val firstToSecond = second.coord - first.coord // vector from first to second
                var currentAntiNode = second.coord
                while  (currentAntiNode.x in 0..<columns && currentAntiNode.y in 0..<rows) {
                    antiNodes.add(currentAntiNode)
                    currentAntiNode += firstToSecond
                }

                val secondToFirst = -firstToSecond
                currentAntiNode = first.coord
                while  (currentAntiNode.x in 0..<columns && currentAntiNode.y in 0..<rows) {
                    antiNodes.add(currentAntiNode)
                    currentAntiNode += secondToFirst
                }
            }
        }
        return antiNodes
    }
}

