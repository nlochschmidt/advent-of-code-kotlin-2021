import java.math.BigInteger
import java.math.BigInteger.ZERO

object Day06 {
    private const val startTimerForNewFish: Byte = 8
    private const val startTimerForExistingFish: Byte = 6
    private const val zero: Byte = 0

    fun part1(input: List<String>): Long {
        val initPopulation = parsePopulation(input.first())

        return simulate(initPopulation, 80).toLong()
    }

    fun part2(input: List<String>): Long {
        val initPopulation = parsePopulation(input.first())

        return simulate(initPopulation, 256).toLong()
    }


    /**
     * The simulation keeps one fish counter per fish age with a special handling of the first two days of new fish.
     */
    private fun simulate(initPopulation: List<Int>, simulationEnd: Int): Long {
        val newFish = arrayOf(0L,0L)
        val generalPopulation = arrayOf(0L,0L,0L,0L,0L,0L,0L)
        initPopulation.forEach { startingPoint ->
            generalPopulation[startingPoint] = generalPopulation[startingPoint].inc()
        }

        repeat(simulationEnd) { day ->
            val currentIndex = (day).mod(7)
            val newFishFromThisRound = generalPopulation[currentIndex]

            generalPopulation[currentIndex] = generalPopulation[currentIndex] + newFish[0]
            newFish[0] = newFish[1]
            newFish[1] = newFishFromThisRound
        }

        return (generalPopulation + newFish).reduce { one, two -> one + two}
    }


    fun parsePopulation(populationStr: String) = populationStr.split(",").map { it.toInt() }
}

fun main() {
    println("Part 1: ${Day06.part1(readInput("Day06"))}")
    println("Part 2: ${Day06.part2(readInput("Day06"))}")
}