import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day01UnitTest : FunSpec({
    context("part1") {

        test("pass the acceptance test") {
            Day01.part1(readInput("Day01_test")) shouldBe 7
        }

        test("should count 0 for one measurement") {
            Day01.part1(listOf("2")) shouldBe 0
        }

        test("should count 0 for same or decreasing measurements") {
            Day01.part1(listOf("2", "2", "1"))
        }

        test("should count up for increasing measurements") {
            Day01.part1(listOf("1", "2")) shouldBe 1
            Day01.part1(listOf("1", "1", "2")) shouldBe 1
            Day01.part1(listOf("1", "2", "3")) shouldBe 2
        }
    }

    context("part2") {
        test("pass the acceptance test") {
            Day01.part2(readInput("Day01_test")) shouldBe 5
        }

        test("not count up if the input is less than the window size of 3") {
            Day01.part2(listOf("1","2","3")) shouldBe 0
        }

        test("not count up if the sum of the two windows is identical") {
            Day01.part2(listOf("1","1","1","1")) shouldBe 0
            Day01.part2(listOf("1","2","2","1")) shouldBe 0
        }

        test("should count up if the sum of the second window is larger than the sum of the first") {
            Day01.part2(listOf("1","1","1","2")) shouldBe 1
            Day01.part2(listOf("1","2","2","2")) shouldBe 1
        }
    }
})