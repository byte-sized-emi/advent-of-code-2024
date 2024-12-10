import me.emilia.Ten
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TenTest {
    @Nested
    inner class PartOne {
        @Test
        fun example() {
            val input = parseInput("""
                89010123
                78121874
                87430965
                96549874
                45678903
                32019012
                01329801
                10456732
            """.trimIndent())
            val sut = Ten(input)
            assertEquals(36, sut.calculate())
        }

        @Test
        fun actualTest() {
            val inputString = NineTest::class.java.getResource("inputs/ten.txt")!!.readText()
            val input = parseInput(inputString)
            val sut = Ten(input)
            assertEquals(0, sut.calculate())
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun example() {
            val input = parseInput("""
                89010123
                78121874
                87430965
                96549874
                45678903
                32019012
                01329801
                10456732
            """.trimIndent())
            val sut = Ten(input)
            assertEquals(81, sut.calculatePartTwo())
        }

        @Test
        fun actualTest() {
            val inputString = NineTest::class.java.getResource("inputs/ten.txt")!!.readText()
            val input = parseInput(inputString)
            val sut = Ten(input)
            assertEquals(0, sut.calculatePartTwo())
        }
    }

    private fun parseInput(input: String): List<List<Int>> =
        input.lines().map {
            it.map { it.digitToInt() }
        }
}