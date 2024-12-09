import me.emilia.Antenna
import me.emilia.Coord
import me.emilia.Eight
import me.emilia.EightPartTwo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EightTest {
    @Nested
    inner class PartOne {
        @Test
        fun simpleTest() {
            val (antennas, columns, rows) = getTestInput()
            val sut = Eight(antennas, columns, rows)
            assertEquals(14, sut.calculate())
        }

        @Test
        fun actualTest() {
            val (antennas, columns, rows) = getActualInput()
            val sut = Eight(antennas, columns, rows)
            assertEquals(0, sut.calculate())
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun simpleTest() {
            val (antennas, columns, rows) = getTestInput()
            val sut = EightPartTwo(antennas, columns, rows)
            assertEquals(34, sut.calculate())
        }

        @Test
        fun actualTest() {
            val (antennas, columns, rows) = getActualInput()
            val sut = EightPartTwo(antennas, columns, rows)
            assertEquals(0, sut.calculate())
        }
    }

    private val testInput = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
        """.trimIndent()

    fun getTestInput() = parseInput(testInput)

    fun getActualInput(): Triple<List<Antenna>, Int, Int> {
        val actualInput = EightTest::class.java.getResource("inputs/eight.txt")!!.readText()
        return parseInput(actualInput)
    }

    fun parseInput(input: String): Triple<List<Antenna>, Int, Int> {
        val antennas = input.lines().flatMapIndexed { rowIndex, line ->
            line.withIndex().filter { it.value.isLetterOrDigit() }
                .map { Antenna(it.value, Coord(it.index, rowIndex)) }
        }

        val columns = input.lines().first().trim().length
        val rows = input.lines().filter { it.isNotBlank() }.size

        return Triple(antennas, columns, rows)
    }
}