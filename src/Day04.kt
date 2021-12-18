object Day04 {
    fun part1(input: List<String>): Int {
        val (draws, boards) = parseDrawsAndBoards(input)
        val (winningBoard, winningDraws) = draws.indices.firstNotNullOf(findWinningBoardAndDraws(draws, boards))
        return winningBoard.score(winningDraws)
    }

    private fun parseDrawsAndBoards(input: List<String>): Pair<List<Int>, List<Board>> {
        val draws = input.first().split(",").map(String::toInt)
        val boards = parseAllBoards(input.drop(2))
        return Pair(draws, boards)
    }

    private fun findWinningBoardAndDraws(
        draws: List<Int>,
        boards: List<Board>
    ) = { index: Int ->
        val currentDraws = draws.subList(0, index)
        val winingBoard = boards.firstOrNull { it.hasWon(currentDraws) }
        winingBoard?.let { winningBoard ->
            winningBoard to currentDraws
        }
    }


    fun part2(input: List<String>): Int {
        val (draws, boards) = parseDrawsAndBoards(input)

        val (loosingBoard, finalDraw) = boards.map { board ->
            draws.indices.firstNotNullOf { index -> findWinningBoardAndDraws(draws, listOf(board))(index) }
        }
            .sortedBy { (_, draws) -> draws.size }
            .last()

        return loosingBoard.score(finalDraw)
    }

    fun parseBoard(inputs: List<String>): Board {
        require(inputs.size == 5)
        return Board(inputs.flatMap { it.trim().split("\\s+".toRegex()).map(String::toInt) })
    }

    fun parseAllBoards(inputs: List<String>): List<Board> {
        return inputs.filter { it.isNotEmpty() }.chunked(5).map(::parseBoard)
    }

    data class Board(val numbers: List<Int>) {
        private val rows = numbers.chunked(5)
        private val columns = rows.fold(List(5) { emptyList<Int>() }) { columns, row ->
            columns.mapIndexed { index, column -> column + row[index] }
        }

        fun hasWon(draws: List<Int>): Boolean {
            return rows.any(draws::containsAll)
                    || columns.any(draws::containsAll)
        }

        fun score(draws: List<Int>): Int {
            val notDrawn = numbers.filterNot { it in draws }
            return notDrawn.sum() * draws.last()
        }

        override fun toString(): String {
            return rows.map { row -> row.map { it.toString().padStart(2) }.joinToString(" ") }.joinToString("\n")
        }
    }
}

fun main() {
    println("Part 1: ${Day04.part1(readInput("Day04"))}")
    println("Part 2: ${Day04.part2(readInput("Day04"))}")
}