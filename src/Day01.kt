import Day01.Change.DECREASE
import Day01.Change.INCREASE
import Day01.part1
import Day01.part2

object Day01 {
    fun part1(input: List<String>): Int {
        return prepareInputs(input)
            .detectChanges()
            .count { it == INCREASE }
        }

    fun part2(input: List<String>): Int {
        return prepareInputs(input)
            .summedUpMeasurementWindows()
            .detectChanges()
            .count { it == INCREASE}
    }

    private enum class Change {
        INCREASE,
        DECREASE
    }

    private fun prepareInputs(input: List<String>) = input
        .asSequence()
        .map(String::toInt)

    private fun Sequence<Int>.detectChanges() =
        windowed(2)
            .map { (previousValue, currentValue) ->
                if (previousValue < currentValue) INCREASE else DECREASE
            }

    private fun Sequence<Int>.summedUpMeasurementWindows() =
        windowed(3).map(List<Int>::sum)
}

fun main() {
    val input = readInput("Day01")
    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}
