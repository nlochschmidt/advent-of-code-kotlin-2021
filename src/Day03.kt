object Day03 {

    fun rowCountAndColumnHighBitCount(input: List<String>): Pair<Int, List<Int>> {
        // Count of binaryNumbers
        var count = 0
        // Count the '1' bits in each column
        val columnHighBitCount = Array(input.first().length) { 0 }
        input.forEach { it ->
            count++
            it.forEachIndexed { index, bit ->
                if (bit == '1') columnHighBitCount[index]++
            }
        }
        return Pair(count, columnHighBitCount.toList())
    }

    fun convertToGamma(columnHighBitCounts: List<Int>, threshold: Int): Int {
        val bitList = columnHighBitCounts.map {
            bitCount -> if (bitCount >= threshold) "1" else "0"
        }
        return bitList.toInt()
    }

    fun convertToEpsilon(columnHighBitCounts: List<Int>, threshold: Int): Int {
        val bitString = columnHighBitCounts.map {
                bitCount -> if (bitCount >= threshold) "0" else "1"
        }
        return bitString.toInt()
    }

    private fun Iterable<String>.toInt(): Int = joinToString(separator = "").toInt(2)

    fun part1(input: List<String>): Int {
        val (count, columnHighBitCounts) = rowCountAndColumnHighBitCount(input)
        val halfOfRowCount = count / 2
        val gamma = convertToGamma(columnHighBitCounts, threshold = halfOfRowCount)
        val epsilon = convertToEpsilon(columnHighBitCounts, threshold = halfOfRowCount)
        return (gamma * epsilon)
    }

     fun findOxygenGeneratorRating(input: List<String>): Int {

         tailrec fun recursiveSearch(remainingInput: List<String>, searchAtIndex: Int, wordLength: Int): String {
             if (remainingInput.size == 1 || searchAtIndex >= wordLength) return remainingInput.first()
             val (startingWithOne, startingWithZero) = remainingInput.partition { it[searchAtIndex] == '1' }
             val continueWith = if (startingWithOne.size >= startingWithZero.size) {
                 startingWithOne
             } else {
                 startingWithZero
             }
             return recursiveSearch(continueWith, searchAtIndex + 1, wordLength)
         }

         return recursiveSearch(input, 0, input.first().length).toInt(2)
    }

    fun findCO2ScrubberRating(input: List<String>): Int {

        tailrec fun recursiveSearch(remainingInput: List<String>, searchAtIndex: Int, wordLength: Int): String {
            if (remainingInput.size == 1 || searchAtIndex >= wordLength) return remainingInput.first()
            val (startingWithOne, startingWithZero) = remainingInput.partition { it[searchAtIndex] == '1' }
            val continueWith = if (startingWithOne.size < startingWithZero.size) {
                startingWithOne
            } else {
                startingWithZero
            }
            return recursiveSearch(continueWith, searchAtIndex + 1, wordLength)
        }

        return recursiveSearch(input, 0, input.first().length).toInt(2)
    }

    fun part2(input: List<String>): Int {
        return findOxygenGeneratorRating(input) * findCO2ScrubberRating(input)
    }
}

fun main() {
    println("Part 1: ${Day03.part1(readInput("Day03"))}")
    println("Part 2: ${Day03.part2(readInput("Day03"))}")
}