import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day07Test : FunSpec({

    context("part1") {
        test("should pass acceptance test") {
            Day07.part1(readInput("Day07_test")) shouldBe 37
        }
    }

    context("part2") {
        test("should pass acceptance test") {
            Day07.part2(readInput("Day07_test")) shouldBe 168
        }
    }
})