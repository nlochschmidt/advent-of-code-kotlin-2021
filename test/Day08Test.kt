import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Day08Test : FunSpec({

    context("part1") {
        test("should pass acceptance test") {
            Day08.part1(readInput("Day08_test")) shouldBe 26
        }

        test("should deduce mapping") {
            Day08.deduceMapping("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab") shouldBe mapOf(
                "abcdefg" to '8',
                "bcdef" to '5',
                "acdfg" to '2',
                "abcdf" to '3',
                "abd" to '7',
                "abcdef" to '9',
                "bcdefg" to '6',
                "abef" to '4',
                "abcdeg" to '0',
                "ab" to '1',
            )
        }
    }

    context("part2") {
        test("should pass acceptance test") {
            Day08.part2(readInput("Day08_test")) shouldBe 61229
        }
    }
})