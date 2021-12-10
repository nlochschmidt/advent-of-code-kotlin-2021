import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe

class Day03Test : FunSpec({

    context("part1") {

        test("should pass acceptance test") {
            Day03.part1(readInput("Day03_test")) shouldBe 198
        }

        withData(
            listOf("1", "0", "0") to 1 * 0, // gamma * epsilon
            listOf("01", "01", "01") to 1 * 2,
            listOf("000", "110", "110") to 6 * 1,
        ) { (inputs, expectedPowerConsumption) ->
            Day03.part1(inputs) shouldBe expectedPowerConsumption
        }

        context("rowCountAndColumnHighBitCount") {
            withData(
                listOf("1") to (1 to listOf(1)),
                listOf("01") to (1 to listOf(0, 1)),
                listOf("000", "110", "110") to (3 to listOf(2, 2, 0)),
            ) { (inputs, expectedResult) ->
                val (actualCount, actualColumnHighBitCount) = Day03.rowCountAndColumnHighBitCount(inputs)
                val (expectedCount, expectedColumnHighBitCount) = expectedResult
                actualCount shouldBe expectedCount
                actualColumnHighBitCount shouldContainInOrder expectedColumnHighBitCount
            }
        }

        test("convertToGamma") {
            Day03.convertToGamma(listOf(5,5,5), 3) shouldBe 4 + 2 + 1
            Day03.convertToGamma(listOf(3,3,3), 3) shouldBe 4 + 2 + 1
            Day03.convertToGamma(listOf(5,3,2,1), 3) shouldBe 8 + 4
            Day03.convertToGamma(listOf(2,2,2), 3) shouldBe 0
            Day03.convertToGamma(listOf(0,0,0), 3) shouldBe 0
        }

        test("convertToEpsilon") {
            Day03.convertToEpsilon(listOf(5,5,5), 3) shouldBe 0
            Day03.convertToEpsilon(listOf(3,3,3), 3) shouldBe 0
            Day03.convertToEpsilon(listOf(5,3,2,1), 3) shouldBe 2 + 1
            Day03.convertToEpsilon(listOf(2,2,2), 3) shouldBe 4 + 2 + 1
            Day03.convertToEpsilon(listOf(0,0,0), 3) shouldBe 4 + 2 + 1
        }
    }

    context("part2") {
        test("should pass acceptance test") {
            Day03.part2(readInput("Day03_test")) shouldBe 230
        }
    }
})
