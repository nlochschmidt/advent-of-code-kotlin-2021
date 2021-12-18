import Day05.Line.LineType.Diagonal
import Day05.Line.LineType.Horizontal
import Day05.Line.LineType.Vertical
import Day05.Point
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave

class Day05Test : FunSpec({

    context("part1") {
        test("should pass acceptance test") {
            Day05.part1(readInput("Day05_test")) shouldBe 5
        }

        val horizontalLine = Day05.Line(Point(0, 9), Point(5, 9))
        val verticalLine = Day05.Line(Point(7, 0), Point(7, 4))
        val diagonalLine = Day05.Line(Point(5, 5), Point(8, 2))

        test("parse input into line") {
            Day05.parseLine("0,9 -> 5,9") shouldBe horizontalLine
            Day05.parseLine("7,0 -> 7,4") shouldBe verticalLine
            Day05.parseLine("5,5 -> 8,2") shouldBe diagonalLine
        }

        test("Line should tell if it's horizontal, vertical or diagonal") {
            horizontalLine.type shouldBe Horizontal
            verticalLine.type shouldBe Vertical
            diagonalLine.type shouldBe Diagonal
        }

        test("Line should produce list of points") {
            Day05.Line(Point(1, 1), Point(1, 3)).points shouldBe listOf(Point(1, 1), Point(1, 2), Point(1, 3))
        }
    }

    context("part2") {
        test("should pass acceptance test") {
            Day05.part2(readInput("Day05_test")) shouldBe 0
        }
    }
})
