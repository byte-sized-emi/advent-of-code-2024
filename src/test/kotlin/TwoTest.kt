import me.emilia.Two
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

    private fun getInputData(): List<List<Int>> {
        val inputFile = TwoTest::class.java.getResource("two.txt")!!.readText()

        return inputFile.lines()
            .map { line ->
                line.split(" ").map { it.toInt() }.toList()
            }
            .toList()
    }

}