import me.emilia.Two
import me.emilia.TwoPartTwo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TwoTest {
    @Test
    fun partOneSimpleTest() {
        val input = listOf(
            listOf(7, 6, 4, 2, 1),
            listOf(1, 2, 7, 8, 9),
            listOf(9, 7, 6, 2, 1),
            listOf(1, 3, 2, 4, 5),
            listOf(8, 6, 4, 4, 1),
            listOf(1, 3, 6, 7, 9)
        )
        val sut = Two(input)
        assertEquals(2, sut.calculate())
    }

    @Test
    fun partOneActualTest() {
        val input = getInputData()
        assertEquals(input.first().size, 6)

        val sut = Two(input)
        assertEquals(0, sut.calculate())
    }

    @Test
    fun partTwoSimpleTest() {
        val input = listOf(
            listOf(7, 6, 4, 2, 1),
            listOf(1, 2, 7, 8, 9),
            listOf(9, 7, 6, 2, 1),
            listOf(1, 3, 2, 4, 5),
            listOf(8, 6, 4, 4, 1),
            listOf(1, 3, 6, 7, 9)
        )
        val sut = TwoPartTwo(input)
        assertEquals(4, sut.calculate())
    }

    @Test
    fun firstElementBadTest() {
        val input = listOf(
            listOf(1, 8, 7, 6, 3, 2),
            listOf(1, 8, 9, 10, 12, 15)
        )
        val sut = TwoPartTwo(input)
        assertEquals(2, sut.calculate())
    }

    @Test
    fun lastElementBadTest() {
        val input = listOf(
            listOf(1, 2, 3,  4, 5, 22),
            listOf(10, 9, 8, 7, 8)
        )
        val sut = TwoPartTwo(input)
        assertEquals(2, sut.calculate())
    }

    @Test
    fun twoBadElementsTest() {
        val input = listOf(
            listOf(1, 2, 10, 3, 4, 5, 22),
            listOf(15, 9, 8, 7, 7),
            listOf(1, 2, 10, 10, 3, 4, 5),
            listOf(1, 10, 2, 3, 10, 4),
        )
        val sut = TwoPartTwo(input)
        assertEquals(0, sut.calculate())
    }

    @Test
    fun oneBadElementInSecondPosition() {
        val input = listOf(
            listOf(1, 1, 3, 5, 7, 9)
        )
        val sut = TwoPartTwo(input)
        assertEquals(1, sut.calculate())
    }

    @Test
    fun partTwoActualTest() {
        val input = getInputData()
        assertEquals(input.first().size, 6)

        val sut = TwoPartTwo(input)
        assertEquals(0, sut.calculate())
    }

    private fun getInputData(): List<List<Int>> {
        val inputFile = TwoTest::class.java.getResource("inputs/two.txt")!!.readText()

        return inputFile.lines()
            .map { line ->
                line.split(" ").map { it.toInt() }.toList()
            }
            .toList()
    }

}