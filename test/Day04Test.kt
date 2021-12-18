import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave

class Day04Test : FunSpec({

    context("part1") {
        test("should pass acceptance test") {
            Day04.part1(readInput("Day04_test")) shouldBe 4512
        }

        test("parseBoard") {
            Day04.parseBoard("""
                22 13 17 11  0
                 8  2 23  4 24
                21  9 14 16  7
                 6 10  3 18  5
                 1 12 20 15 19
            """.trimIndent().lines()) shouldBe Day04.Board(
                listOf(
                    22, 13, 17, 11, 0, 8, 2, 23, 4, 24, 21, 9, 14, 16, 7, 6, 10, 3, 18, 5, 1, 12, 20, 15, 19
                )
            )
        }

        test("parseBoards") {
            Day04.parseAllBoards(
                """
                    22 13 17 11  0
                     8  2 23  4 24
                    21  9 14 16  7
                     6 10  3 18  5
                     1 12 20 15 19

                     3 15  0  2 22
                     9 18 13 17  5
                    19  8  7 25 23
                    20 11 10 24  4
                    14 21 16 12  6
                """.trimIndent().lines()
            ) shouldHaveSize (2)
        }

        test("hasWon") {
            val board = Day04.parseBoard(
                """
                14 21 17 24  4
                10 16 15  9 19
                18  8 23 26 20
                22 11 13  6  5
                 2  0 12  3  7
            """.trimIndent().lines()
            )

            board.hasWon(emptyList()) shouldBe false
            board.hasWon(listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21)) shouldBe false
            board.hasWon(listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24)) shouldBe true
        }

        test("score") {
            val board = Day04.parseBoard(
                """
                14 21 17 24  4
                10 16 15  9 19
                18  8 23 26 20
                22 11 13  6  5
                 2  0 12  3  7
            """.trimIndent().lines()
            )

            board.score(listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24)) shouldBe 4512
        }
    }

    context("part2") {
        test("should pass acceptance test") {
            Day04.part2(readInput("Day04_test")) shouldBe 1924
        }
    }
})
