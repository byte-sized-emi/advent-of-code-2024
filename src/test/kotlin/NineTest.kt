import me.emilia.Nine
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NineTest {
    @Nested
    inner class PartOne {
        @Test
        fun example() {
            val input = parseInput("2333133121414131402")
            val sut = Nine(input)
            assertEquals(1928, sut.calculate())
        }

        @Test
        fun actualTest() {
            val inputString = NineTest::class.java.getResource("inputs/nine.txt")!!.readText()
            val input = parseInput(inputString)
            val sut = Nine(input)
            assertEquals(0L, sut.calculate())
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun example() {
            val input = parseInput("2333133121414131402")
            val sut = Nine(input)
            assertEquals(2858, sut.calculatePartTwo())
        }

        @Test
        fun actualTest() {
            val inputString = NineTest::class.java.getResource("inputs/nine.txt")!!.readText()
            val input = parseInput(inputString)
            val sut = Nine(input)
            assertEquals(0L, sut.calculatePartTwo())
        }
    }

    private fun parseInput(line: String) =
        line.map { it.toString().toInt() }
}