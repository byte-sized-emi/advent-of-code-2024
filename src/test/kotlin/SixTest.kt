import me.emilia.Five
import me.emilia.Six
import me.emilia.SixPartTwo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SixTest {

    @Nested
    inner class PartOne {
        @Test
        fun simpleTest() {
            val map = getTestData()
            val sut = Six(map)
            assertEquals(41, sut.calculate())
        }

        @Test
        fun actualInputTest() {
            val input = getInputData()
            val sut = Six(input)
            assertEquals(0, sut.calculate())
        }
    }

    @Nested
    inner class PartTwo {
        @Test
        fun simpleTest() {
            val map = getTestData()
            val sut = SixPartTwo(map)
            assertEquals(6, sut.calculate())
        }

        @Test
        fun actualInputTest() {
            val input = getInputData()
            val sut = SixPartTwo(input)
            assertEquals(0, sut.calculate())
        }
    }

    private fun getTestData() = """
                    ....#.....
                    .........#
                    ..........
                    ..#.......
                    .......#..
                    ..........
                    .#..^.....
                    ........#.
                    #.........
                    ......#...
                """.trimIndent()

    private fun getInputData() =
        TwoTest::class.java.getResource("inputs/six.txt")!!.readText()

}