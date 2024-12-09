import me.emilia.Equation
import me.emilia.Seven
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.math.sign
import kotlin.test.assertEquals

class SevenTest {
    @Test
    fun simpleTest() {
        val equations = listOf(
            Equation(190, listOf(10, 19)),
            Equation(3267, listOf(81, 40, 27)),
            Equation(83, listOf(17, 5)),
            Equation(156, listOf(15, 6)),
            Equation(7290, listOf(6, 8, 6, 15)),
            Equation(161011, listOf(16, 10, 13)),
            Equation(192, listOf(17, 8, 14)),
            Equation(21037, listOf(9, 7, 18, 13)),
            Equation(292, listOf(11, 6, 16, 20)),
        )
        val sut = Seven(equations)
        assertEquals(3749, sut.calculate())
    }

    @Test
    fun actualTest() {
        val equations = getInputData()
        assertEquals(432832280199, equations.first().solution)
        assertEquals(10, equations.last().inputs.size)
        val sut = Seven(equations)
        assertEquals(0, sut.calculate())
    }


    private fun getInputData() =
        TwoTest::class.java.getResource("inputs/seven.txt")!!.readText()
            .lines()
            .map { line ->
                val solution = line.split(':').first().toLong()
                val inputs = line.split(':').last()
                    .trim().split(' ')
                    .map { it.toLong() }

                Equation(solution, inputs)
            }
}