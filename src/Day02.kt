import Command.Down
import Command.Forward
import Command.Up

private data class SubmarinePosition(val horizontalPosition: HorizontalPosition, val depth: Depth) {
    fun moveForwardBy(value: Int): SubmarinePosition = copy(horizontalPosition = horizontalPosition + value)
    fun increaseDepthBy(value: Int): SubmarinePosition = changeDepthBy(value)
    fun changeDepthBy(value: Int): SubmarinePosition = copy(depth = depth + value)
    fun decreaseDepthBy(value: Int): SubmarinePosition = copy(depth = depth - value)

    val horizonalPositionMultipliedWithDepth: Int get() = horizontalPosition.value * depth.value
}

@JvmInline
private value class HorizontalPosition(val value: Int) {
    operator fun plus(distance: Int) = HorizontalPosition(value + distance)
}

@JvmInline
private value class Depth(val value: Int) {
    operator fun plus(depth: Int) = Depth(value + depth)
    operator fun minus(depth: Int) = Depth(value - depth)
}

private sealed interface Command {
    @JvmInline value class Forward(val value: Int) : Command
    @JvmInline value class Down(val value: Int) : Command
    @JvmInline value class Up(val value: Int) : Command

    companion object {
        fun parseAll(input: List<String>) =
            input
                .map {
                    val (command, numberString) = it.split(" ")
                    val number = numberString.toInt()
                    when (command) {
                        "forward" -> Forward(number)
                        "down" -> Down(number)
                        "up" -> Up(number)
                        else -> throw IllegalArgumentException("'$command' is not a valid command")
                    }
                }
                .asSequence()
    }
}

private class Part1Submarine(
    var position: SubmarinePosition = SubmarinePosition(HorizontalPosition(0), Depth(0))
) {

    fun steer(commands: Sequence<Command>): SubmarinePosition {
        commands.forEach(this::giveCommand)
        return position
    }

    private fun giveCommand(command: Command) {
        position = when (command) {
            is Forward -> position.moveForwardBy(command.value)
            is Down -> position.increaseDepthBy(command.value)
            is Up -> position.decreaseDepthBy(command.value)
        }
    }
}

private class Part2Submarine(
    var position: SubmarinePosition = SubmarinePosition(HorizontalPosition(0), Depth(0))
) {
    class Aim(val diveFactor: Int) {
        fun pointDown(down: Int): Aim = Aim(diveFactor + down)
        fun pointUp(up: Int): Aim = Aim(diveFactor - up)
        fun moveForwardFrom(position: SubmarinePosition, movement: Int): SubmarinePosition {
            return position
                .changeDepthBy(diveFactor * movement)
                .moveForwardBy(movement)
        }
    }

    var aim: Aim = Aim(0)

    fun steer(commands: Sequence<Command>): SubmarinePosition {
        commands.forEach(this::giveCommand)
        return position
    }

    private fun giveCommand(command: Command) {
        when (command) {
            is Down -> aim = aim.pointDown(command.value)
            is Up -> aim = aim.pointUp(command.value)
            is Forward -> position = aim.moveForwardFrom(position, command.value)
        }
    }
}

object Day02 {
    fun part1(input: List<String>): Int {
        val submarine = Part1Submarine()
        val commands = Command.parseAll(input)
        val lastKnownPosition = submarine.steer(commands)
        return lastKnownPosition.horizonalPositionMultipliedWithDepth
    }

    fun part2(input: List<String>): Int {
        val submarine = Part2Submarine()
        val commands = Command.parseAll(input)
        val lastKnownPosition = submarine.steer(commands)
        return lastKnownPosition.horizonalPositionMultipliedWithDepth
    }
}

fun main() {
    println("Part 1: ${Day02.part1(readInput("Day02"))}")
    println("Part 2: ${Day02.part2(readInput("Day02"))}")
}