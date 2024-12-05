import me.emilia.One
import me.emilia.OnePartTwo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class OneTest {

    @Test
    fun simpleTest() {
        val left = mutableListOf(3, 4, 2, 1, 3, 3)
        val right = mutableListOf(4, 3, 5, 3, 9, 3)
        val sut = One(left, right)
        assertEquals(11, sut.calculate())
    }

    @Test
    fun actualCalculationTest() {
        val (left, right) = getInputData()

        val sut = One(left, right)
        assertEquals(0, sut.calculate())
    }

    @Test
    fun secondPartTest() {
        val left = mutableListOf(3, 4, 2, 1, 3, 3)
        val right = mutableListOf(4, 3, 5, 3, 9, 3)
        val sut = OnePartTwo(left, right)
        assertEquals(31, sut.calculate())
    }

    @Test
    fun secondPartCalculationTest() {
        val (left, right) = getInputData()

        val sut = OnePartTwo(left, right)
        assertEquals(0, sut.calculate())
    }

    private fun getInputData(): Pair<List<Int>, List<Int>> {
        val inputFile = OneTest::class.java.getResource("one.txt")!!.readText()

        val (left, right) = inputFile.lines()
            .map { line -> Pair(line.substring(0..<5), line.substring(8..<13)) }
            .map { (first, second) -> Pair(first.toInt(), second.toInt()) }
            .toList().unzip()

        assertEquals(98415, left.first())
        assertEquals(86712, right.first())
        assertEquals(1000, left.size)
        assertEquals(1000, right.size)

        return Pair(left, right)
    }
}