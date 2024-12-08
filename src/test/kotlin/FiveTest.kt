import me.emilia.Five
import me.emilia.FivePartTwo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FiveTest {

    @Nested
    inner class PartOne {
        @Test
        fun simpleTest() {
            val (ordering, updates) = getSimpleTestData()

            val sut = Five(ordering, updates)
            assertEquals(sut.calculate(), actual = 143)
        }

        @Test
        fun actualTest() {
            val (ordering, updates) = getInputData()
            val sut = Five(ordering, updates)
            assertEquals(sut.calculate(), actual = 0)
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun simpleTest() {
            val (ordering, updates) = getSimpleTestData()

            val sut = FivePartTwo(ordering, updates)
            assertEquals(sut.calculate(), actual = 123)
        }

        @Test
        fun actualTest() {
            val (ordering, updates) = getInputData()
            val sut = FivePartTwo(ordering, updates)
            assertEquals(sut.calculate(), actual = 0)
        }
    }

    private fun getSimpleTestData(): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val ordering = listOf(
            Pair(47, 53),
            Pair(97, 13),
            Pair(97, 61),
            Pair(97, 47),
            Pair(75, 29),
            Pair(61, 13),
            Pair(75, 53),
            Pair(29, 13),
            Pair(97, 29),
            Pair(53, 29),
            Pair(61, 53),
            Pair(97, 53),
            Pair(61, 29),
            Pair(47, 13),
            Pair(75, 47),
            Pair(97, 75),
            Pair(47, 61),
            Pair(75, 61),
            Pair(47, 29),
            Pair(75, 13),
            Pair(53, 13),
        )

        val updates = listOf(
            listOf(75, 47, 61, 53, 29),
            listOf(97, 61, 53, 29, 13),
            listOf(75, 29, 13),
            listOf(75, 97, 47, 61, 53),
            listOf(61, 13, 29),
            listOf(97, 13, 75, 29, 47),
        )
        return Pair(ordering, updates)
    }

    private fun getInputData(): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val inputFile = TwoTest::class.java.getResource("inputs/five.txt")!!.readText()

        val splitIndex = inputFile.indexOf("\n\n")
        val orderingString = inputFile.substring(0, splitIndex + 1).trim()
        val ordering = orderingString.lines()
            .map { it.split('|')}
            .map { Pair(it.first().toInt(), it.last().toInt()) }

        val updatesString = inputFile.substring(splitIndex + 1).trim()
        val updates = updatesString.lines()
            .map { it.split(',').map { it.toInt() }}

        return Pair(ordering, updates)
    }

}