import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day06Test : FunSpec({

    context("part1") {
        test("should pass acceptance test") {
            Day06.part1(readInput("Day06_test")) shouldBe 5934
        }

        test("parsePopulation") {
            Day06.parsePopulation("3,4,3,1,2") shouldBe listOf(3,4,3,1,2)
        }
    }

    context("part2") {
        test("should pass acceptance test") {
            Day06.part2(readInput("Day06_test")) shouldBe 26984457539
        }
    }
})
