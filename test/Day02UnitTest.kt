import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class Day02UnitTest : FunSpec({

    context("part1") {
        test("pass the acceptance test") {
            Day02.part1(readInput("Day02_test")) shouldBe 150
        }

        withData(
            listOf("down 1") to 0 * 0, // horizontal * depth,
            listOf("forward 1") to 1 * 0,
            listOf("forward 1", "down 1") to 1 * 1,
            listOf("forward 1", "down 5", "up 3") to 1 * 2,
            listOf("forward 10", "down 5") to 10 * 5,

        ) { (commands, expectedLocation) ->
            Day02.part1(commands) shouldBe expectedLocation
        }
    }

    context("part2") {
        test("pass the acceptance test") {
            Day02.part2(readInput("Day02_test")) shouldBe 900
        }

        withData(
            listOf("down 1") to 0 * 0, // horizontal * depth,
            listOf("forward 1") to 1 * 0,
            listOf("forward 1", "down 1") to 1 * 0,
            listOf("down 1", "forward 1") to 1 * 1,
            listOf("down 1", "forward 5") to 5 * 5,
            listOf("down 2", "forward 5") to 5 * 10,
            listOf("down 5", "forward 1", "up 3", "forward 1") to 2 * 7,
            listOf("down 5", "forward 10") to 10 * 50,

            ) { (commands, expectedLocation) ->
            Day02.part2(commands) shouldBe expectedLocation
        }
    }
})
