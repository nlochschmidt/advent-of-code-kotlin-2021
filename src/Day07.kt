import java.lang.Math.abs
import java.lang.Math.min

object Day07 {
    fun part1(input: List<String>): Int {
        return findMinMovement(input)
    }

    private fun findMinMovement(input: List<String>, fuelFunction: (Int) -> Int = { it }): Int {
        val startPositions = input.first().parseInts()
        val maxPosition = startPositions.maxOf { it }
        val minPosition = startPositions.minOf { it }

        return (minPosition..maxPosition).fold(Int.MAX_VALUE) { minFuel, candidatePosition ->
            val fuelForThisPosition = startPositions.fold(0) { fuel, positionOfCrab ->
                fuel + fuelFunction(abs(candidatePosition - positionOfCrab))
            }
            min(fuelForThisPosition, minFuel)
        }
    }

    fun part2(input: List<String>): Int {
        return findMinMovement(input, fuelFunction = { distance -> (distance * (distance + 1) / 2)})
    }
}

fun main() {
    println("Part 1: ${Day07.part1(readInput("Day07"))}")
    println("Part 2: ${Day07.part2(readInput("Day07"))}")
}