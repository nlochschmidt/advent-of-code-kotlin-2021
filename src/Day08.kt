object Day08 {
    fun part1(input: List<String>): Int {
        val allOutputs = input.map { it.extractOutputsSorted() }
        return allOutputs.map { outputs -> outputs.count { listOf(2,3,4,7).contains(it.length) } }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map {
            val wireMapping = deduceMapping(it)
            it.extractOutputsSorted().map { wireMapping[it] }.joinToString("").toInt()
        }.sum()
    }

    fun deduceMapping(input: String): Map<String, Char> {
        val eight = "abcdefg"
        val allChars = eight.toSet()
        val inputs = input.extractInputsSorted().toMutableList()
        inputs.remove(eight)

        val one = inputs.first { it.length == 2 }.also(inputs::remove)
        val seven = inputs.first { it.length == 3 }.also(inputs::remove)
        val four = inputs.first { it.length == 4 }.also(inputs::remove)

        val nine = inputs.first { it.length == 6 && it.intersectsWith(four) }.also(inputs::remove)
        val zero = inputs.first { it.length == 6 && it.intersectsWith(one) }.also(inputs::remove)
        val six = inputs.first { it.length == 6 }.also(inputs::remove)
        val three = inputs.first { it.intersectsWith(one) }.also(inputs::remove)

        val leftBottomWire = allChars.subtract(nine.toSet()).single()
        val two = inputs.first { it.contains(leftBottomWire) }.also(inputs::remove)
        val five = inputs.single()

        return mapOf(
            zero to '0',
            one to '1',
            two to '2',
            three to '3',
            four to '4',
            five to '5',
            six to '6',
            seven to '7',
            eight to '8',
            nine to '9',
        )
    }

    private fun String.intersectsWith(character: String) = toSet().intersect(character.toSet()) == character.toSet()

    private fun String.extractInputsSorted(): List<String> =
        split(" | ").component1().readSignalList()

    private fun String.extractOutputsSorted(): List<String> =
        split(" | ").component2().readSignalList()

    private fun String.readSignalList(): List<String> =
        split(" ").map { it.toCharArray().sorted().joinToString("") }
}

fun main() {
    println("Part 1: ${Day08.part1(readInput("Day08"))}")
    println("Part 2: ${Day08.part2(readInput("Day08"))}")
}